package com.illustration_api.illustration_api.RestAPI.Controllers;

import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Category.ICategoryService;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.History.HistoryModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.ServiceResult.ServiceResult;
import com.illustration_api.illustration_api.Infrastructure.Interfaces.Services.History.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HistoryController {

    @Autowired
    private IHistoryService _service;

    @PostMapping("/History/Create")
    public ServiceResult save(@Valid @RequestBody HistoryModel model){

        // validation
        var result = _service.Create(model);

        return result;
    }

    @GetMapping("/History/List")
    public ServiceResult<List<HistoryModel>> fetchList(){

        var result = _service.GetAll();
        return result;
    }

    @GetMapping("/History/GetBy/{name}")
    public ServiceResult<HistoryModel> fetchByName(@PathVariable("name") String name) {
        var result = _service.GetByName(name);
        return result;
    }

    @DeleteMapping("/History/DeleteByName/{name}")
    public ServiceResult deleteByName(@PathVariable("name") String name){
        var result = _service.DeleteByName(name);

        return  result;
    }
    @DeleteMapping("/History/DeleteById/{name}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        var result = _service.DeleteById(id);

        return  !result ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not deleted"):
                ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }

    @PutMapping("/History/Update/{id}")
    public ServiceResult  update(@PathVariable("id") Long id, @RequestBody HistoryModel model){
        model.setId(id);
        var result = _service.Update(model);

        return result;
    }
}

package com.illustration_api.illustration_api.RestAPI.Controllers;

import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Items.IItemService;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private IItemService _service;

    @PostMapping("/Item/Create")
    public ResponseEntity<String> save(@Valid @RequestBody ItemModel model){
        // validation
        var result = _service.Create(model);

        return result == null ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not created"):
                ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }

    @GetMapping("/Item/List")
    public ResponseEntity<List<ItemModel>> fetchList(){

        var result = _service.GetAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/Item/ListByCategory/{category}")
    public ResponseEntity<List<ItemModel>> fetchListByCategory(@PathVariable("category") String category){

        var result = _service.GetAllByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/Item/GetBy/{name}")
    public ResponseEntity<ItemModel> fetchByName(@PathVariable("name") String name) {
        var result = _service.GetByName(name);

        return result == null ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null):
                ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/Item/DeleteById/{name}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        var result = _service.DeleteById(id);

        return  !result ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not deleted"):
                ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }

    @PutMapping("/Item/Update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody ItemModel model){
        model.setId(id);
        var result = _service.Update(model);

        return  !result ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not updated"):
                ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }
}

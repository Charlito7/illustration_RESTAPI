package com.illustration_api.illustration_api.RestAPI.Controllers;

import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Category.ICategoryService;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private ICategoryService _service;

    @PostMapping("/Category/Create")
    public ResponseEntity<String> save(@Valid @RequestBody CategoryModel model){
        // validation
        var result = _service.Create(model);


        return result == null ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not created"):
                ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }

    @GetMapping("/Category/List")
    public ResponseEntity<List<CategoryModel>> fetchList(){

        var result = _service.GetAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/Category/GetBy/{name}")
    public ResponseEntity<CategoryModel> fetchByName(@PathVariable("name") String name) {
        var result = _service.GetByName(name);

    return result == null ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null):
                ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/Category/DeleteByName/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable("name") String name){
        var result = _service.DeleteByName(name);

        return  !result ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not deleted"):
                ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }
    @DeleteMapping("/Category/DeleteById/{name}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        var result = _service.DeleteById(id);

        return  !result ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not deleted"):
                ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }

    @PutMapping("/Category/Update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody CategoryModel model){
        model.setId(id);
        var result = _service.Update(model);

        return  !result ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not updated"):
                ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }
}

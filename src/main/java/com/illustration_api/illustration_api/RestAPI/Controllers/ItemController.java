package com.illustration_api.illustration_api.RestAPI.Controllers;

import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Items.IItemService;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private IItemService _service;


    @PostMapping("Item/Create")
    public ResponseEntity<String> Create(@RequestBody ItemModel model){
        //Model Validation pending

        var result = _service.Create(model);
        if(result == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Item failed");
        }
       return ResponseEntity.status(HttpStatus.OK).body("Item added successfully");
    }

    public String test(){
        return "Hell0";
    }
}

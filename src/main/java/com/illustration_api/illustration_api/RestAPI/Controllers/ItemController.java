package com.illustration_api.illustration_api.RestAPI.Controllers;

import com.illustration_api.illustration_api.Infrastructure.Interfaces.Services.Items.IItemService;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.ServiceResult.ServiceResult;
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
    public ServiceResult<List<ItemModel>> fetchList(){

        var result = _service.GetAll();
        return  result;
    }
    @GetMapping("/Item/ListByCategory/{category}")
    public ServiceResult<List<ItemModel>> fetchListByCategory(@PathVariable("category") String category){

        var result = _service.GetAllByCategory(category);
        return result;
    }

    @GetMapping("/Item/GetBy/{name}")
    public ServiceResult<List<ItemModel>> fetchByName(@PathVariable("name") String name) {
        var result = _service.GetByName(name);

        return result;
    }

    @DeleteMapping("/Item/DeleteById/{name}")
    public ServiceResult deleteById(@PathVariable("id") Long id){
        var result = _service.DeleteById(id);

        return  result;
    }

    @PutMapping("/Item/Update/{id}")
    public ServiceResult update(@PathVariable("id") Long id, @RequestBody ItemModel model){
        model.setId(id);
        var result = _service.Update(model);

        return  result;
    }

/*    @GetMapping(params = { "page", "size" })
    public List<ItemModel> findPaginated(@RequestParam("page") int page,
                                   @RequestParam("size") int size, UriComponentsBuilder uriBuilder,
                                   HttpServletResponse response) {
        Page<ItemModel> resultPage = _service.FindPaginated(page, size);
 *//*       if (page > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }*//*
        *//*eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Foo>(
                Foo.class, uriBuilder, response, page, resultPage.getTotalPages(), size));*//*

        return resultPage.getContent();
    }*/
}

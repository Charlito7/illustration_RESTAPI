package com.illustration_api.illustration_api.Infrastructure.Services.Category;

import com.illustration_api.illustration_api.Core.Application.Common.Constants.RequestMethod;
import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Category.ICategoryService;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.ServiceResult.ServiceResult;
import com.illustration_api.illustration_api.Core.Application.Repository.ICategoryRepository;
import com.illustration_api.illustration_api.Core.Domain.Entities.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository _repository;


    @Override
    public ServiceResult Create(CategoryModel model) {

        CategoryEntity category = new CategoryEntity();
        category.setName(model.getName());
        category.setDescription(model.getDescription());

        var result = _repository.save(category);

        ServiceResult response = new ServiceResult();

        if(result == null){
            response.ServiceStatus(HttpStatus.INTERNAL_SERVER_ERROR, false);
            return response;
        }
        return response;
    }

    @Override
    public ServiceResult Update(CategoryModel model) {

        // Response
        ServiceResult response = new ServiceResult();
        //validation model
        if(model.getName().isBlank() || model.getName().isEmpty()){
            response.ServiceStatus(HttpStatus.BAD_REQUEST, false);
            response.AddError(HttpStatus.BAD_REQUEST, "Name cannot null or empty");
            return response;
        }
        //check if the category exist
         var category =_repository.findById(model.getId()).get();
        if(category == null){
            response.ServiceStatus(HttpStatus.NO_CONTENT, false);
            response.AddError(HttpStatus.NO_CONTENT,"Category not exist");
            return response;
        }
        category.setName(model.getName());
        category.setDescription(model.getDescription());

        var result = _repository.save(category);


        if(result == null){
            response.ServiceStatus(HttpStatus.INTERNAL_SERVER_ERROR, false);
            response.AddError(HttpStatus.INTERNAL_SERVER_ERROR,"Category not create");
            return response;
        }

        return response;
    }

    @Override
    public ServiceResult DeleteByName(String name) {
        ServiceResult response = new ServiceResult();

        if(name.isBlank() || name.isEmpty()){
            response.ServiceStatus(HttpStatus.BAD_REQUEST, false);
            response.AddError(HttpStatus.BAD_REQUEST, "Name cannot null or empty");
            return response;
        }
        //check if the category exist
        var category =_repository.findByName(name);
        if(category == null){
            response.ServiceStatus(HttpStatus.NO_CONTENT, false);
            response.AddError(HttpStatus.NO_CONTENT,"Category not exist");
            return response;
        }
        category.setIsDelete(true);
        var result = _repository.save(category);

        if(result == null){
            response.ServiceStatus(HttpStatus.INTERNAL_SERVER_ERROR, false);
            response.AddError(HttpStatus.INTERNAL_SERVER_ERROR,"Category not delete");
            return response;
        }

        return response;
    }
    @Override
    public Boolean DeleteById(Long id) {
        //check if the category exist
        var category =_repository.findById(id).get();
        if(category == null){
            return false;
        }
        category.setIsDelete(true);
        var result = _repository.save(category);

        return result != null ? true : false;
    }
    @Override
    public ServiceResult<CategoryModel> GetByName(String category) {
        ServiceResult response = new ServiceResult();

        var req = _repository.findByName(category);
        if(req == null){
            response.ServiceStatus(HttpStatus.NO_CONTENT, false);
            response.AddError(HttpStatus.NO_CONTENT,"Category not exist");
            return response;
        }
        var result = new CategoryModel();
        result.setId(req.getId());
        result.setName(req.getName());
        result.setDescription(req.getDescription());

        return new ServiceResult<CategoryModel>(result);
    }

    @Override
    public ServiceResult<List<CategoryModel>> GetAll() {

        var req = _repository.findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

        // Response
        ServiceResult<List<CategoryModel>> response = new ServiceResult<List<CategoryModel>>(req);
        response.ServiceStatus(HttpStatus.OK, true);

        return response;
    }

 /*   public List<CategoryModel> GetSpecification(String name, String description) {
        //check if the category exist
        var cat =
        var category =_repository.findById(id).get();
        if(category == null){
            return false;
        }
        category.setIsDelete(true);
        var result = _repository.save(category);

        return result != null ? true : false;
    }*/



    private CategoryModel convert(CategoryEntity entity){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(entity.getName());
        categoryModel.setDescription(entity.getDescription());
        categoryModel.setId(entity.getId());

        return categoryModel;

    }
}

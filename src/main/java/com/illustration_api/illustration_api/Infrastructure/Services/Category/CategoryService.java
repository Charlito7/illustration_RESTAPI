package com.illustration_api.illustration_api.Infrastructure.Services.Category;

import com.illustration_api.illustration_api.Core.Application.Common.Constants.RequestMethod;
import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Category.ICategoryService;
import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Mappings.Category.IMapCategoryModelToCategoryEntity;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;
import com.illustration_api.illustration_api.Core.Application.Repository.ICategoryRepository;
import com.illustration_api.illustration_api.Core.Domain.Entities.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository _repository;
    @Autowired
    private IMapCategoryModelToCategoryEntity mapCategoryModelToCategoryEntity;
    @Override
    public CategoryModel Create(CategoryModel model) {

        CategoryEntity category = new CategoryEntity();
        category.setName(model.getName());
        category.setDescription(model.getDescription());

        var result = _repository.save(category);

        return result != null ? model : null;
    }

    @Override
    public Boolean Update(CategoryModel model) {
        //validation model
        if(model.getName().isBlank() || model.getName().isEmpty()){
            return null;
        }
        //check if the category exist
         var category =_repository.findById(model.getId()).get();
        if(category == null){
            return null;
        }
        category.setName(model.getName());
        category.setDescription(model.getDescription());

        var result = _repository.save(category);

        return result != null ? true : false;
    }

    @Override
    public Boolean DeleteByName(String name) {
        if(name.isBlank() || name.isEmpty()){
            return null;
        }
        //check if the category exist
        var category =_repository.findByName(name);
        if(category == null){
            return null;
        }
        category.setIsDelete(true);
        var result = _repository.save(category);

        return result != null ? true : false;
    }

    @Override
    public CategoryModel GetByName(String category) {

        var req = _repository.findByName(category);
        if(req == null){
            return null;
        }
        var result = new CategoryModel();
        result.setId(req.getId());
        result.setName(req.getName());
        result.setDescription(req.getDescription());

        return result;
    }

    @Override
    public List<CategoryModel> GetAll() {

        var req = _repository.findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
        return req;
    }

    private CategoryModel convert(CategoryEntity entity){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(entity.getName());
        categoryModel.setDescription(entity.getDescription());
        categoryModel.setId(entity.getId());

        return categoryModel;

    }
}

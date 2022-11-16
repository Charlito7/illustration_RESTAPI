package com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Category;

import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.ServiceResult.ServiceResult;

import java.util.List;

public interface ICategoryService {
    ServiceResult Create(CategoryModel model);
    ServiceResult Update(CategoryModel model);
    ServiceResult DeleteByName(String name);
    ServiceResult<CategoryModel> GetByName(String category);
    ServiceResult<List<CategoryModel>> GetAll();

    Boolean DeleteById(Long id);
}

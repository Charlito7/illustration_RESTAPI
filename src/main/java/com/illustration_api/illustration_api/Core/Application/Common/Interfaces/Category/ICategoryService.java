package com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Category;

import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;

import java.util.List;

public interface ICategoryService {
    CategoryModel Create(CategoryModel model);
    Boolean Update(CategoryModel model);
    Boolean DeleteByName(String name);
    CategoryModel GetByName(String category);
    List<CategoryModel> GetAll();
}

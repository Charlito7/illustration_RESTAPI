package com.illustration_api.illustration_api.Infrastructure.Interfaces.Repositories;

import com.illustration_api.illustration_api.Core.Domain.Entities.CategoryEntity;

import java.util.List;

public interface ICategoryRepositoriesCustom {
    List<CategoryEntity> findCategoryByNameAndDescription(String Name, String description);
}
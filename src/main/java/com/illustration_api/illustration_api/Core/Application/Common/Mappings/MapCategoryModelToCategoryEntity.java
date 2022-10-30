package com.illustration_api.illustration_api.Core.Application.Common.Mappings;

import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Mappings.Category.IMapCategoryModelToCategoryEntity;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;
import com.illustration_api.illustration_api.Core.Domain.Entities.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapCategoryModelToCategoryEntity implements IMapCategoryModelToCategoryEntity {
    @Override
    public void updateCustomerFromDto(List<CategoryEntity> category, List<CategoryModel> model) {

    }
}

package com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Mappings.Category;

import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;
import com.illustration_api.illustration_api.Core.Domain.Entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapCategoryModelToCategoryEntity {
    void updateCustomerFromDto(List<CategoryEntity> category, @MappingTarget List<CategoryModel> model);
}

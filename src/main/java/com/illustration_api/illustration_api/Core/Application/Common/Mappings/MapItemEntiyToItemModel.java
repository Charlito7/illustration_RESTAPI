package com.illustration_api.illustration_api.Core.Application.Common.Mappings;

import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Domain.Entities.CategoryEntity;
import com.illustration_api.illustration_api.Core.Domain.Entities.ItemEntity;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapItemEntiyToItemModel {
    public void updateCustomerFromDto(List<ItemEntity> category, @MappingTarget List<ItemModel> model) {

    }
}

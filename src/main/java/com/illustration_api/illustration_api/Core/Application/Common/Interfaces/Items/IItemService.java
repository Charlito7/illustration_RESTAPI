package com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Items;


import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;

import java.util.List;

public interface IItemService {
    Boolean Create(ItemModel model);
    Boolean Update(ItemModel model);
    Boolean DeleteById(Long id);
    List<ItemModel> GetAllByCategory(String category);
    List<ItemModel> GetAll();
    ItemModel GetByName(String name);
}

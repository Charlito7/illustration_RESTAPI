package com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Items;


import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;

public interface IItemService {
    ItemModel Create(ItemModel model);
    ItemModel Update(ItemModel model);
    ItemModel Delete(Long id);
    ItemModel GetAllByCategory(String category);
    ItemModel GetAll();
}

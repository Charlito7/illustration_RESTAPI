package com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Items;


import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.ServiceResult.ServiceResult;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IItemService {
    ServiceResult Create(ItemModel model);
    ServiceResult Update(ItemModel model);
    ServiceResult DeleteById(Long id);
    ServiceResult<List<ItemModel>> GetAllByCategory(String category);
    ServiceResult<List<ItemModel>> GetAll();
    ServiceResult<List<ItemModel>> GetByName(String name);

    Page<ItemModel> FindPaginated(int page, int size);
}

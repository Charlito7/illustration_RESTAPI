package com.illustration_api.illustration_api.Infrastructure.Interfaces.Services.History;

import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.History.HistoryModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.ServiceResult.ServiceResult;

import java.util.List;

public interface IHistoryService {

    ServiceResult Create(HistoryModel model);
    ServiceResult Update(HistoryModel model);
    ServiceResult DeleteByName(String name);
    ServiceResult<HistoryModel> GetByName(String category);
    ServiceResult<List<HistoryModel>> GetAll();

    Boolean DeleteById(Long id);
}

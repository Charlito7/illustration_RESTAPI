package com.illustration_api.illustration_api.Infrastructure.Services.History;

import com.illustration_api.illustration_api.Core.Domain.Entities.ItemEntity;
import com.illustration_api.illustration_api.Infrastructure.Interfaces.Services.Items.IItemService;
import com.illustration_api.illustration_api.Core.Application.Common.Models.History.HistoryModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.ServiceResult.ServiceResult;
import com.illustration_api.illustration_api.Core.Application.Repository.ICategoryRepository;
import com.illustration_api.illustration_api.Core.Domain.Entities.HistoryEntity;
import com.illustration_api.illustration_api.Infrastructure.Interfaces.Repositories.History.IHistoryRepository;
import com.illustration_api.illustration_api.Infrastructure.Interfaces.Services.History.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService implements IHistoryService {

    @Autowired
    private IHistoryRepository _repository;

    @Autowired
    private ICategoryRepository _categoryRepository;

    @Autowired
    private IItemService _itemService;

    @Override
    public ServiceResult Create(HistoryModel model) {

        //recuperate category
        var category = _categoryRepository.findByName(model.getCategoryName());

        HistoryEntity History = new HistoryEntity();
        History.setCategory(category);
        History.setName(model.getName());
        History.setDescription(model.getDescription());
        History.setCoverPath(model.getCoverPath());
        History.setAuthor(model.getAuthor());
        History.setCredit(model.getCredit());
        History.setTags(model.getTags());

        var result = _repository.save(History);

        ServiceResult response = new ServiceResult();

        if(result == null){
            response.ServiceStatus(HttpStatus.INTERNAL_SERVER_ERROR, false);
            return response;
        }

        // Save the different illustration
        for (ItemModel item: model.getItems()
        ) {
            // Attribute the history id to the item elem
            item.setHistoryId(result.getId());
            var res = _itemService.Create(item);

        }
        return response;
    }

    @Override
    public ServiceResult Update(HistoryModel model) {

        // Response
        ServiceResult response = new ServiceResult();
        //validation model
        if(model.getName().isBlank() || model.getName().isEmpty()){
            response.ServiceStatus(HttpStatus.BAD_REQUEST, false);
            response.AddError(HttpStatus.BAD_REQUEST, "Name cannot null or empty");
            return response;
        }
        //check if the History exist
        var History =_repository.findById(model.getId()).get();
        if(History == null){
            response.ServiceStatus(HttpStatus.NO_CONTENT, false);
            response.AddError(HttpStatus.NO_CONTENT,"History not exist");
            return response;
        }
        History.setName(model.getName());
        History.setDescription(model.getDescription());

        var result = _repository.save(History);


        if(result == null){
            response.ServiceStatus(HttpStatus.INTERNAL_SERVER_ERROR, false);
            response.AddError(HttpStatus.INTERNAL_SERVER_ERROR,"History not create");
            return response;
        }

        return response;
    }

    @Override
    public ServiceResult DeleteByName(String name) {
        ServiceResult response = new ServiceResult();

        if(name.isBlank() || name.isEmpty()){
            response.ServiceStatus(HttpStatus.BAD_REQUEST, false);
            response.AddError(HttpStatus.BAD_REQUEST, "Name cannot null or empty");
            return response;
        }
        //check if the History exist
        var History =_repository.findByName(name);
        if(History == null){
            response.ServiceStatus(HttpStatus.NO_CONTENT, false);
            response.AddError(HttpStatus.NO_CONTENT,"History not exist");
            return response;
        }
        History.setIsDelete(true);
        var result = _repository.save(History);

        if(result == null){
            response.ServiceStatus(HttpStatus.INTERNAL_SERVER_ERROR, false);
            response.AddError(HttpStatus.INTERNAL_SERVER_ERROR,"History not delete");
            return response;
        }

        return response;
    }
    @Override
    public Boolean DeleteById(Long id) {
        //check if the History exist
        var History =_repository.findById(id).get();
        if(History == null){
            return false;
        }
        History.setIsDelete(true);
        var result = _repository.save(History);

        return result != null ? true : false;
    }
    @Override
    public ServiceResult<HistoryModel> GetByName(String History) {
        ServiceResult response = new ServiceResult();

        var req = _repository.findByName(History);
        if(req == null){
            response.ServiceStatus(HttpStatus.NO_CONTENT, false);
            response.AddError(HttpStatus.NO_CONTENT,"History not exist");
            return response;
        }
        var result = new HistoryModel();
        result.setId(req.getId());
        result.setName(req.getName());
        result.setDescription(req.getDescription());

        return new ServiceResult<HistoryModel>(result);
    }

    @Override
    public ServiceResult<List<HistoryModel>> GetAll() {

        var req = _repository.findAll(Sort.by("items.position"))
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

        // Response
        ServiceResult<List<HistoryModel>> response = new ServiceResult<List<HistoryModel>>(req);
        response.ServiceStatus(HttpStatus.OK, true);

        return response;
    }

    private HistoryModel convert(HistoryEntity entity){
        HistoryModel HistoryModel = new HistoryModel();
        HistoryModel.setName(entity.getName());
        HistoryModel.setDescription(entity.getDescription());
        HistoryModel.setId(entity.getId());
        HistoryModel.setTags(entity.getTags());
        HistoryModel.setCategoryName(entity.getCategory().getName());
        HistoryModel.setCoverPath(entity.getCoverPath());
        HistoryModel.setAuthor(entity.getAuthor());
        HistoryModel.setCredit(entity.getCredit());
        HistoryModel.setLanguage(entity.getLanguage());
        HistoryModel.setIsbn(entity.getIsbn());

        var itemsModel = entity.getItems()
                .stream()
                .map(this::convertItem)
                .collect(Collectors.toList());

       HistoryModel.setItems(itemsModel);

        return HistoryModel;

    }

    private ItemModel convertItem(ItemEntity entity){
        ItemModel itemModel = new ItemModel();
        itemModel.setId(entity.getId());
        itemModel.setName(entity.getName());
        itemModel.setDescription(entity.getDescription());
        itemModel.setTags(entity.getTags());
/*        itemModel.setCoverPath(entity.getCoverPath());
        itemModel.setAuthor(entity.getAuthor());
        itemModel.setCredit(entity.getCredit());
        itemModel.setLanguage(entity.getLanguage());
        itemModel.setIsbn(entity.getIsbn());*/
        itemModel.setPath(entity.getPath());
        itemModel.setCategoryName(entity.getCategory().getName());
        itemModel.setPosition(entity.getPosition());

        return itemModel;

    }
}

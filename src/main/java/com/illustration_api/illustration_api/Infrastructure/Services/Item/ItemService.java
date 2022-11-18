package com.illustration_api.illustration_api.Infrastructure.Services.Item;

import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Items.IItemService;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.ServiceResult.ServiceResult;
import com.illustration_api.illustration_api.Core.Application.Repository.ICategoryRepository;
import com.illustration_api.illustration_api.Core.Application.Repository.IItemRepository;
import com.illustration_api.illustration_api.Core.Domain.Entities.CategoryEntity;
import com.illustration_api.illustration_api.Core.Domain.Entities.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService implements IItemService {

    @Autowired
    private IItemRepository _repository;
    @Autowired
    private ICategoryRepository _categoryRepository;

    @Override
    public ServiceResult Create(ItemModel model) {

        //recuperate category
        var category = _categoryRepository.findByName(model.getCategoryName());

        ItemEntity item = new ItemEntity();
        item.setName(model.getName());
        item.setDescription(model.getDescription());
        item.setTags(model.getTags());
        item.setCoverPath(model.getCoverPath());
        item.setAuthor(model.getAuthor());
        item.setCredit(model.getCredit());
        item.setLanguage(model.getLanguage());
        item.setIsbn(model.getIsbn());
        item.setPath(model.getPath());
        item.setCategory(category);

        var result = _repository.save(item);
        ServiceResult response = new ServiceResult();
        if(result == null){
            response.ServiceStatus(HttpStatus.INTERNAL_SERVER_ERROR, false);
            return response;
        }
        return response;

    }

    @Override
    public ServiceResult Update(ItemModel model) {
        // Response
        ServiceResult response = new ServiceResult();
        var oldEntity =_repository.findById(model.getId()).get();
        if(oldEntity == null){
            response.ServiceStatus(HttpStatus.NO_CONTENT, false);
            response.AddError(HttpStatus.NO_CONTENT,"Item  not exist");
            return response;
        }

        //recuperate category
        var category = _categoryRepository.findByName(model.getCategoryName());

        oldEntity.setName(model.getName());
        oldEntity.setDescription(model.getDescription());
        oldEntity.setTags(model.getTags());
        oldEntity.setCoverPath(model.getCoverPath());
        oldEntity.setAuthor(model.getAuthor());
        oldEntity.setCredit(model.getCredit());
        oldEntity.setLanguage(model.getLanguage());
        oldEntity.setIsbn(model.getIsbn());
        oldEntity.setPath(model.getPath());
        oldEntity.setCategory(category);

        var result = _repository.save(oldEntity);

        if(result == null){
            response.ServiceStatus(HttpStatus.INTERNAL_SERVER_ERROR, false);
            response.AddError(HttpStatus.INTERNAL_SERVER_ERROR,"Item not create");
            return response;
        }

        return response;
    }

    @Override
    public ServiceResult DeleteById(Long id) {
        ServiceResult response = new ServiceResult();
        //check if the category exist
        var item =_repository.findById(id).get();
        if(item == null){
            response.ServiceStatus(HttpStatus.NO_CONTENT, false);
            response.AddError(HttpStatus.NO_CONTENT,"Item not exist");
            return response;
        }
        item.setIsDelete(true);
        var result = _repository.save(item);

        if(result == null){
            response.ServiceStatus(HttpStatus.INTERNAL_SERVER_ERROR, false);
            response.AddError(HttpStatus.INTERNAL_SERVER_ERROR,"item not create");
            return response;
        }

        return response;
    }

    @Override
    public ServiceResult<List<ItemModel>> GetAllByCategory(String category) {
        var response = new ServiceResult();
        if(category == null){
            response.ServiceStatus(HttpStatus.BAD_REQUEST, false);
            response.AddError(HttpStatus.BAD_REQUEST,"Category  cannot null");
            return response;
        }
        //check if the category exist
        var item =_categoryRepository.findByName(category);
        if(item == null){
            response.ServiceStatus(HttpStatus.INTERNAL_SERVER_ERROR, false);
            response.AddError(HttpStatus.INTERNAL_SERVER_ERROR,"Category not create");
            return response;
        }
        var req = _repository.findByCategory(item.getId())
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
        // Response
        ServiceResult<List<ItemModel>> responseList = new ServiceResult<List<ItemModel>>(req);
        responseList.ServiceStatus(HttpStatus.OK, true);

        return responseList;
    }

    @Override
    public ServiceResult<List<ItemModel>> GetAll() {
        var req = _repository.findAll(Sort.by("categoryName"))
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
        // Response
        ServiceResult<List<ItemModel>> response = new ServiceResult<List<ItemModel>>(req);
        response.ServiceStatus(HttpStatus.OK, true);

        return response;
    }

    @Override
    public ServiceResult<List<ItemModel>> GetByName(String name) {
        var response = new ServiceResult();

        var entity = _repository.findByName(name)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());


        if(entity == null){
            response.ServiceStatus(HttpStatus.NO_CONTENT, false);
            response.AddError(HttpStatus.NO_CONTENT,"Item not exist");
            return response;
        }

/*        ItemModel itemModel = new ItemModel();
        itemModel.setName(entity.getName());
        itemModel.setDescription(entity.getDescription());
        itemModel.setTags(entity.getTags());
        itemModel.setCoverPath(entity.getCoverPath());
        itemModel.setAuthor(entity.getAuthor());
        itemModel.setCredit(entity.getCredit());
        itemModel.setLanguage(entity.getLanguage());
        itemModel.setIsbn(entity.getIsbn());
        itemModel.setPath(entity.getPath());
        itemModel.setCategoryName(entity.getCategory().getName());*/

        ServiceResult<List<ItemModel>> responseItem = new ServiceResult<List<ItemModel>>(entity);
        responseItem.ServiceStatus(HttpStatus.OK, true);

        return responseItem;
    }

    @Override
    public Page<ItemModel> FindPaginated(int page, int size) {
        return null;
    }

    private ItemModel convert(ItemEntity entity){
        ItemModel itemModel = new ItemModel();
        itemModel.setId(entity.getId());
        itemModel.setName(entity.getName());
        itemModel.setDescription(entity.getDescription());
        itemModel.setTags(entity.getTags());
        itemModel.setCoverPath(entity.getCoverPath());
        itemModel.setAuthor(entity.getAuthor());
        itemModel.setCredit(entity.getCredit());
        itemModel.setLanguage(entity.getLanguage());
        itemModel.setIsbn(entity.getIsbn());
        itemModel.setPath(entity.getPath());
        itemModel.setCategoryName(entity.getCategory().getName());

        return itemModel;

    }
}

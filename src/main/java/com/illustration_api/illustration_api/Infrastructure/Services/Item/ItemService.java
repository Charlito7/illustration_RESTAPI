package com.illustration_api.illustration_api.Infrastructure.Services.Item;

import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Items.IItemService;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Category.CategoryModel;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Application.Repository.ICategoryRepository;
import com.illustration_api.illustration_api.Core.Application.Repository.IItemRepository;
import com.illustration_api.illustration_api.Core.Domain.Entities.CategoryEntity;
import com.illustration_api.illustration_api.Core.Domain.Entities.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public Boolean Create(ItemModel model) {

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

        return result != null ? true : false;
    }

    @Override
    public Boolean Update(ItemModel model) {

        var oldEntity =_repository.findById(model.getId()).get();
        if(oldEntity == null){
            return false;
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

        return result != null ? true : false;
    }

    @Override
    public Boolean DeleteById(Long id) {
        //check if the category exist
        var item =_repository.findById(id).get();
        if(item == null){
            return false;
        }
        item.setIsDelete(true);
        var result = _repository.save(item);

        return result != null ? true : false;
    }

    @Override
    public List<ItemModel> GetAllByCategory(String category) {
        if(category == null){
          return   this.GetAll();
        }
        //check if the category exist
        var item =_categoryRepository.findByName(category);
        if(item == null){
               return this.GetAll();
        }
        var req = _repository.findByCategory(item.getId())
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
        return req;
    }

    @Override
    public List<ItemModel> GetAll() {
        var req = _repository.findAll(Sort.by("categoryName"))
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
        return req;
    }

    @Override
    public ItemModel GetByName(String name) {
        var entity = _repository.findByName(name);
        if(entity == null){
            return null;
        }

        ItemModel itemModel = new ItemModel();
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

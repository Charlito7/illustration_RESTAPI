package com.illustration_api.illustration_api.Infrastructure.Services.Item;

import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.Items.IItemService;
import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Application.Repository.IItemRepository;
import com.illustration_api.illustration_api.Core.Domain.Entities.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements IItemService {

    @Autowired
    private IItemRepository _repository;

    @Override
    public ItemModel Create(ItemModel model) {

        //recuperate category

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

        var result = _repository.save(item);

        return result != null ? model : null;
    }

    @Override
    public ItemModel Update(ItemModel model) {
        var req =_repository.findById(4L);
        return null;
    }

    @Override
    public ItemModel Delete(Long id) {
        return null;
    }

    @Override
    public ItemModel GetAllByCategory(String category) {
        return null;
    }

    @Override
    public ItemModel GetAll() {
        return null;
    }
}

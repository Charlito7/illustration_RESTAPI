package com.illustration_api.illustration_api.Core.Application.Repository;

import com.illustration_api.illustration_api.Core.Domain.Entities.ItemEntity;

import java.util.List;
import java.util.Set;

public interface IUserRepositoryCustom {
    List<ItemEntity> findItems(Set<String> emails);
}

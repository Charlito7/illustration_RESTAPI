package com.illustration_api.illustration_api.Core.Application.Repository;

import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Domain.Entities.ItemEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface IItemRepository extends JpaRepository<ItemEntity, Long> {
    @Query(
            value = "SELECT * FROM ITEM u WHERE u.category_id = ?1",
            nativeQuery = true)
    List<ItemEntity> findByCategory(Long category_id);
    ItemEntity findByName(String name);
}

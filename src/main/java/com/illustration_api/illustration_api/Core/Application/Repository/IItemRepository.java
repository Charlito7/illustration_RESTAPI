package com.illustration_api.illustration_api.Core.Application.Repository;

import com.illustration_api.illustration_api.Core.Domain.Entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository extends JpaRepository<ItemEntity, Long> {
}

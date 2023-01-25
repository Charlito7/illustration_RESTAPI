package com.illustration_api.illustration_api.Infrastructure.Interfaces.Repositories.History;

import com.illustration_api.illustration_api.Core.Domain.Entities.CategoryEntity;
import com.illustration_api.illustration_api.Core.Domain.Entities.HistoryEntity;
import com.illustration_api.illustration_api.Core.Domain.Entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHistoryRepository extends JpaRepository<HistoryEntity, Long> {
    @Query(
            value = "SELECT * FROM HISTORY h LEFT JOIN ITEM i ON h.id = i.history_id GROUP BY h.name ORDER BY i.position",
            nativeQuery = true)
    HistoryEntity findByName(String history);
}
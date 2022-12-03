package com.illustration_api.illustration_api.Infrastructure.Interfaces.Repositories;

import com.illustration_api.illustration_api.Core.Domain.Entities.CategoryEntity;
import com.illustration_api.illustration_api.Infrastructure.Interfaces.Repositories.ICategoryRepositoriesCustom;
import com.illustration_api.illustration_api.Infrastructure.Interfaces.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface ICategoryRepositories extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {
    static Specification<CategoryEntity> nameContains(String name) {
        return (book, cq, cb) -> cb.like(book.get("title"), "%" + name + "%");
    }

    static Specification<CategoryEntity> titleContains(String title) {
        return (book, cq, cb) -> cb.like(book.get("title"), "%" + title + "%");
    }
}

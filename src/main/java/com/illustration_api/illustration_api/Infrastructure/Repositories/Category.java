package com.illustration_api.illustration_api.Infrastructure.Repositories;

import com.illustration_api.illustration_api.Core.Domain.Entities.CategoryEntity;
import com.illustration_api.illustration_api.Infrastructure.Interfaces.Repositories.ICategoryRepositoriesCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Category implements ICategoryRepositoriesCustom {
    EntityManager em;

    // constructor

    @Override
    public List<CategoryEntity> findCategoryByNameAndDescription(String Name, String description) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> cq = cb.createQuery(CategoryEntity.class);

        Root<CategoryEntity> category = cq.from(CategoryEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Name != null) {
            predicates.add(cb.like(category.get("name"), "%" + Name + "%"));
        }
        if (description != null) {
            predicates.add(cb.like(category.get("description"), "%" + description + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));
       /* Predicate descriptionPredicate = cb.equal(category.get("description"), description);
        Predicate namePredicate = cb.like(category.get("name"), "%" + Name + "%");
        cq.where(namePredicate, descriptionPredicate);*/

        TypedQuery<CategoryEntity> query = em.createQuery(cq);
        return query.getResultList();
    }
}

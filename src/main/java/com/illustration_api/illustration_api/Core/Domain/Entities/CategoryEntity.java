package com.illustration_api.illustration_api.Core.Domain.Entities;

import com.illustration_api.illustration_api.Core.Domain.Common.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "category")
public class CategoryEntity extends AuditableEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private Boolean IsDelete;

    @OneToMany(mappedBy = "category")
    private List<ItemEntity> items;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "history")
    private List<ItemEntity> histories;
}

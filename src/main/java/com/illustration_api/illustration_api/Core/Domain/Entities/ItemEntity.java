package com.illustration_api.illustration_api.Core.Domain.Entities;

import com.illustration_api.illustration_api.Core.Domain.Common.AuditableEntity;
import com.illustration_api.illustration_api.Core.Domain.Enum.Language;
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
@Table(name = "item")
public class ItemEntity extends AuditableEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable =false)
    private String name;
    private String description;
    private String tags;
    private String path;
    private Integer position;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private Long size;
    @Column(nullable = false)
    private String extension;
    private Boolean isVisible;
    private Boolean isDelete;


    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

    // Many to one relationship with History entity
    @ManyToOne
    @JoinColumn(name="history_id")
    private HistoryEntity history;


}

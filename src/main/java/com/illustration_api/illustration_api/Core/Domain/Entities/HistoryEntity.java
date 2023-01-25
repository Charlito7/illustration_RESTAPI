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
@Table(name = "history")
public class HistoryEntity extends AuditableEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable =false)
    private String name;
    private String description;
    private String tags;
    private String coverPath;
    private String author;
    private String credit;
    @Enumerated(EnumType.STRING)
    private Language language;
    private String isbn;
    private Boolean isVisible;
    private Boolean isDelete;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST,mappedBy = "history")
    private List<ItemEntity> items;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

}

package com.illustration_api.illustration_api.Core.Domain.Entities;

import com.illustration_api.illustration_api.Core.Domain.Common.AuditableEntity;
import com.illustration_api.illustration_api.Core.Domain.Enum.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "item")
public class ItemEntity extends AuditableEntity {
    @Id
    private Long id;
    @Column(nullable =false)
    private String name;
    private String description;
    private String tags;
    private String path;
    private String coverPath;
    private String author;
    private String credit;
    @Enumerated(EnumType.STRING)
    private Language language;
    private String isbn;
    private Boolean isVisible;

    @OneToOne
    @JoinColumn(name = "itemsInfo_id")
    private ItemInfoEntity itemsInfo;

    @ManyToOne
    @JoinColumn(name="library_id")
    private CategoryEntity category;

}

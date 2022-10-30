package com.illustration_api.illustration_api.Core.Domain.Entities;

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
@Table(name = "itemInfo")
public class ItemInfoEntity {
    @Id
    private Long id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private Long size;
    @Column(nullable = false)
    private String extension;

    @OneToOne(mappedBy = "itemsInfo")
    private ItemEntity items;

}

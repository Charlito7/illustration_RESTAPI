package com.illustration_api.illustration_api.Core.Application.Common.Models.History;


import com.illustration_api.illustration_api.Core.Application.Common.Models.Item.ItemModel;
import com.illustration_api.illustration_api.Core.Domain.Entities.ItemEntity;
import com.illustration_api.illustration_api.Core.Domain.Enum.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryModel {
    private Long id;
    @NotBlank
    private String name;
    private String description;
    private String tags;
    private String categoryName;
    private String coverPath;
    private String author;
    private String credit;
    @Enumerated(EnumType.STRING)
    private Language language;
    private String isbn;
   private List<ItemModel> items;

}

package com.illustration_api.illustration_api.Core.Application.Common.Models.Item;

import com.illustration_api.illustration_api.Core.Domain.Enum.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {
    private Long id;
    private Long historyId;
    @NotBlank
    private String name;
    private String description;
    private String tags;
    @NotBlank
    private String path;
    @NotBlank
    private String coverPath;
    @NotBlank
    private String author;
    private String credit;
    @NotBlank
    private String type;
    @NotNull
    private Long size;
    @Enumerated(EnumType.STRING)
    private Language language;
    private String isbn;
    private String extension;
    private String historyName;
    private String categoryName;
    private Integer position;
}

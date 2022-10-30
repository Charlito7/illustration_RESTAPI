package com.illustration_api.illustration_api.Core.Application.Common.Models.Item;

import com.illustration_api.illustration_api.Core.Domain.Enum.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {
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
    private Language language;
    private String isbn;
}

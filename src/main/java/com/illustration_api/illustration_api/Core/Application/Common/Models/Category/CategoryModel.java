package com.illustration_api.illustration_api.Core.Application.Common.Models.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
    private Long id;
    @NotBlank
    private String name;
    private String description;

}

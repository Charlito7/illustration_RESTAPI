package com.illustration_api.illustration_api.Core.Application.Common.Models.Requests.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {
    private Long id;
    @NotBlank
    private String name;
    private String description;
    private Boolean IsDelete;
}

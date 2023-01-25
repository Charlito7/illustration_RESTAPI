package com.illustration_api.illustration_api.Core.Application.Common.Models.Requests.Category;

import com.illustration_api.illustration_api.Core.Domain.Enum.SearchOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCategoryRequest {
    private String key;
    private SearchOperation operation;
    private Object value;
}

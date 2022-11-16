package com.illustration_api.illustration_api.Core.Application.Common.Models.ServiceResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceError {
    private HttpStatus code;

    private String description;
}

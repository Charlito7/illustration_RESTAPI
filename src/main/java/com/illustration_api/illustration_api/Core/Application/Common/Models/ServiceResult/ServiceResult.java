package com.illustration_api.illustration_api.Core.Application.Common.Models.ServiceResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ServiceResult<T> {
    private boolean isOk;
    private boolean isError;
    private HttpStatus status;
    private List<ServiceError> errorMessages;
    private T result;

    public ServiceResult(HttpStatus status, boolean isOk)
    {
        this.status = status;
        this.isOk = isOk;
    }

    public ServiceResult()
    {
        this.status = HttpStatus.OK;
        this.isOk = true;
    }

    public ServiceResult(T result) {
        this.status = HttpStatus.OK;
        this.isOk = true;
        this.result = result;
    }

    public void ServiceStatus(HttpStatus code, boolean isOk){
            this.status = code;
            this.isOk = isOk;
            this.isError = !isOk;
    }
    public void AddError(HttpStatus code, String description)
    {
        ServiceError error = new ServiceError(code, description);
        errorMessages.add((error));
    }
}

package com.illustration_api.illustration_api.Core.Domain.Common;

import java.util.Date;

public class AuditableEntity {
    public Date Created;
    public String CreatedBy;
    public Date LastModified;
    public String LastModifiedBy;
    public Date Deleted;
    public String DeletedBy;
    public Boolean IsDeleted;
}

package com.illustration_api.illustration_api.Core.Application.Common.Interfaces;

import org.springframework.security.access.hierarchicalroles.RoleHierarchy;

public interface IUserSecurity {
    public RoleHierarchy roleHierarchy();
}

package com.illustration_api.illustration_api.Core.Application.Common.Behaviours;

import com.illustration_api.illustration_api.Core.Application.Common.Interfaces.IUserSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

public class WebSecurityHandler {
    public IUserSecurity  _userSecurity;
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(_userSecurity.roleHierarchy());
        return expressionHandler;
    }
}

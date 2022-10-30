package com.illustration_api.illustration_api.Infrastructure.Common.Interfaces;

import com.illustration_api.illustration_api.Core.Application.Common.Models.UserModel;
import com.illustration_api.illustration_api.Core.Domain.Entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    public UserEntity registerNewUserAccount(UserModel accountDto);
}

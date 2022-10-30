package com.illustration_api.illustration_api.Infrastructure.Services.User;

import com.illustration_api.illustration_api.Core.Application.Common.Models.UserModel;
import com.illustration_api.illustration_api.Core.Application.Repository.*;
import com.illustration_api.illustration_api.Core.Domain.Entities.UserEntity;
import com.illustration_api.illustration_api.Core.Domain.Entities.UserPrivilegeEntity;
import com.illustration_api.illustration_api.Core.Domain.Entities.UserRoleEntity;
import com.illustration_api.illustration_api.Infrastructure.Common.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private IUserService _service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private IUserRoleRepository _roleRepository;

/*    @Override
    public UserEntity registerNewUserAccount(UserModel accountDto) throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException
                    ("There is an account with that email adress: " + accountDto.getEmail());
        }
        UserEntity user = new UserEntity();

        user.setFirstname(accountDto.getFirstname());
        user.setLastname(accountDto.getLastname());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());

        user.setRoles(Arrays.asList(_roleRepository.findByName("ROLE_USER")));
        return _userRepository.save(user);
    }*/

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        UserEntity user = _userRepository.findByEmail(email);
        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    getAuthorities(Arrays.asList(
                            _roleRepository.findByName("ROLE_USER"))));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                true, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<UserRoleEntity> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<UserRoleEntity> roles) {

        List<String> privileges = new ArrayList<>();
        List<UserPrivilegeEntity> collection = new ArrayList<>();
        for (UserRoleEntity role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (UserPrivilegeEntity item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    @Override
    public UserEntity registerNewUserAccount(UserModel accountDto) {
        return null;
    }
}

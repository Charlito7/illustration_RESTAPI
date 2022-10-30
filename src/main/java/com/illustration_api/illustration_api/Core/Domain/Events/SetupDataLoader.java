/*
package com.illustration_api.illustration_api.Core.Domain.Events;

import com.illustration_api.illustration_api.Core.Application.Repository.IUserPrivilegeRepository;
import com.illustration_api.illustration_api.Core.Application.Repository.IUserRepository;
import com.illustration_api.illustration_api.Core.Application.Repository.IUserRoleRepository;
import com.illustration_api.illustration_api.Core.Domain.Entities.UserEntity;
import com.illustration_api.illustration_api.Core.Domain.Entities.UserPrivilegeEntity;
import com.illustration_api.illustration_api.Core.Domain.Entities.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Service
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {
*/
/*
    boolean alreadySetup = false;

   @Autowired
   private IUserRepository _userRepository;

    @Autowired
    private IUserRoleRepository _roleRepository;

    @Autowired
    private IUserPrivilegeRepository _privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        UserPrivilegeEntity readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        UserPrivilegeEntity writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<UserPrivilegeEntity> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        UserRoleEntity adminRole = _roleRepository.findByName("ROLE_ADMIN");
        UserEntity user = new UserEntity();
//        user.setFirstName("Test");
//        user.setLastName("Test");
//        user.setPassword(passwordEncoder.encode("test"));
//        user.setEmail("test@test.com");
//        user.setRoles(Arrays.asList(adminRole));
//        user.setEnabled(true);
        _userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    UserPrivilegeEntity createPrivilegeIfNotFound(String name) {

        UserPrivilegeEntity privilege = _privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new UserPrivilegeEntity();
            privilege.setName(name);
            _privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    UserRoleEntity createRoleIfNotFound(
            String name, Collection<UserPrivilegeEntity> privileges) {

        UserRoleEntity role = _roleRepository.findByName(name);
        if (role == null) {
            role = new UserRoleEntity();
            role.setName(name);
            role.setPrivileges(privileges);
            _roleRepository.save(role);
        }
        return role;
    }*//*

}*/

package com.illustration_api.illustration_api.Core.Application.Repository;

import com.illustration_api.illustration_api.Core.Domain.Entities.UserPrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserPrivilegeRepository extends JpaRepository<UserPrivilegeEntity, Long> {

    UserPrivilegeEntity findByName(String name);
}

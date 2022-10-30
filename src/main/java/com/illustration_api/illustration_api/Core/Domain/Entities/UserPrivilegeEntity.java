package com.illustration_api.illustration_api.Core.Domain.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "userPrivilege")
public class UserPrivilegeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<UserRoleEntity> roles;


}

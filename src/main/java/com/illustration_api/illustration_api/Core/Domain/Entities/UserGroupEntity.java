package com.illustration_api.illustration_api.Core.Domain.Entities;

import com.illustration_api.illustration_api.Core.Domain.Common.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "userGroup")
public class UserGroupEntity extends AuditableEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

}

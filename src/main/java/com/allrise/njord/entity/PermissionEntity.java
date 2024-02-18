package com.allrise.njord.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NJD_PERMISSION")
public class PermissionEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 60)
    private String name;

    @Column(name = "SLUG", nullable = false, length = 60, unique = true)
    private String slug;

    @Column(name = "KEY", nullable = false, length = 60, unique = true)
    private String key;

    @Column(name = "DESCRIPTION", length = 120)
    private String description;

    @ManyToMany(targetEntity = RoleEntity.class)
    @JoinTable(name = "NJD_ROLE_PERMISSION",
            joinColumns = {
                    @JoinColumn(name = "PERMISSION_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<RoleEntity> setOfRoleEntity;

}

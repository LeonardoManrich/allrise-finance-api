package com.allrise.njord.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NJD_USER")
public class UserEntity extends AbstractEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_USER")
    @SequenceGenerator(name = "GEN_NJD_USER", sequenceName = "GEN_NJD_USER")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", length = 120, unique = true)
    private String email;

    @Column(name = "EMAIL_VERIFIED_AT")
    private Timestamp emailVerifiedAt;

    @Column(name = "PASSWORD", length = 64)
    private String password;

    @Column(name = "REMEMBER_TOKEN")
    private String rememberToken;

    @Column(name = "LAST_LOGIN_IP")
    private String lastLoginIp;

    @Column(name = "LAST_LOGIN_DATE")
    private Timestamp lastLoginDate;

    @Column(name = "LAST_LOGIN_BROWSER")
    private String lastLoginBrowser;

    @Column(name = "LAST_LOGIN_COORDINATES")
    private String lastLoginCoordinates;

    @ManyToMany(targetEntity = PermissionEntity.class)
    @JoinTable(name = "NJD_USER_PERMISSION",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "PERMISSION_ID")
            }
    )
    private Set<PermissionEntity> setOfPermissionEntity;

    @ManyToMany(targetEntity = RoleEntity.class)
    @JoinTable(name = "NJD_USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<RoleEntity> setOfRoleEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

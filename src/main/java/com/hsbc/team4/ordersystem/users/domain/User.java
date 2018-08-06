package com.hsbc.team4.ordersystem.users.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import com.hsbc.team4.ordersystem.roles.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users
 * @Description :
 * @Date : 2018/8/3
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails{
    /**
     * The account id
     */
    @Id
    @ApiModelProperty(name = "id")
    private String id;
    /**
     * username，include phone or email or loginName
     */
    @NotBlank(message = "The username can not be empty")
    @ApiModelProperty(name = "username")
    private String username;

    @NotBlank(message = "The phone can not be empty")
    @ApiModelProperty(name = "phone")
    private String phone;

    @NotBlank(message = "The email can not be empty")
    @ApiModelProperty(name = "email")
    private String email;
    /**
     * password
     */
    @NotBlank(message = "The password can not be empty")
    @ApiModelProperty(name = "password")
    @JsonIgnore
    private String password;
    @NotEmpty(message = "the roles can not be empty")
    @ManyToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    private List<Role> roles;
    /**
     * locked
     */
    @Column(name = "locked" ,columnDefinition = "Boolean default false")
    private Boolean locked=false;

    /**
     * expired
     */
    @Column(name = "expired" ,columnDefinition = "Boolean default false")
    private Boolean expired;
    /**
     * lastLoginTime
     */
    @NotNull(message = "The lastLoginTime can not be empty")
    @ApiModelProperty(name = "lastLoginTime")
    private long lastLoginTime;

    /**
     * User
     * @param user
     */
    public User(User user){
        if(user !=null){
            this.setId(user.getId());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setRoles(user.getRoles());
            this.setLocked(user.getExpired());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<Role> roles = this.getRoles();
        for (Role role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return auths;
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
/*
package com.getir.readingisgood.config;

import com.getir.readingisgood.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.springframework.data.mongodb.core.aggregation.MergeOperation.builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrinciple implements UserDetails {

    private String id;

    private String userName;

    private Long phoneNumber;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    public static UserPrinciple constructUserPrincipal(Customer customer) {
        return builder()
                .id(customer.getId())
                .phoneNumber(customer.getPhoneNumber())
                .password(customer.getPassword())
                .userName(customer.getFirstName())
                .authorities(new ArrayList<>())
                .build();
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

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
*/

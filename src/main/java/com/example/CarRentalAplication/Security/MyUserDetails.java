package com.example.CarRentalAplication.Security;

import com.example.CarRentalAplication.models.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private  String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorityList;



    public MyUserDetails(Client client){
        this.userName = client.getName();
        this.password = client.getPassword();
        this.active = getActive(client);
        this.authorityList = Arrays.stream(client.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public MyUserDetails(){

    }

    public  Boolean getActive(Client client){
        if(client.getActive() == 0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
        return this.active;
    }
}

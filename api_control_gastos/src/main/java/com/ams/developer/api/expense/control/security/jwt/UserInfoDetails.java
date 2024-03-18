package com.ams.developer.api.expense.control.security.jwt;

import com.ams.developer.api.expense.control.model.UsuariosModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//Implementa el objeto userDetails
public class UserInfoDetails implements UserDetails {

    private static final long serialVersionUID = 1L; // nos quita un warning (mo es obligatoria)
    private String name;
    private String password;
    private List<GrantedAuthority> authorities; // va a ir a buscar los roles que eventualmente estan configurados dentro del mismo sprinboot

    public UserInfoDetails(UsuariosModel usuariosModel){
        name = usuariosModel.getNombre();
        password = usuariosModel.getPassword();
        authorities = Arrays.stream(usuariosModel.getNombre().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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

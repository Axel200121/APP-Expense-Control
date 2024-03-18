package com.ams.developer.api.expense.control.security.jwt;

import com.ams.developer.api.expense.control.model.UsuariosModel;
import com.ams.developer.api.expense.control.repository.EstadosRepository;
import com.ams.developer.api.expense.control.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Este servicio va realizar la implementación de user detail service
@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadosRepository estadosRepository;

    @Override //este metodo donde se hace toda la configuración, este revise el username, en esta caso sera nuestro correo
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuariosModel> userDeatil = this.usuarioRepository.findByCorreoAndEstadosId(username, this.estadosRepository.findById("cc98363d-86c7-4576-a8da-ef256d337de3").orElse(null));

        // Retornamos la variable
        return userDeatil.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));

    }
}

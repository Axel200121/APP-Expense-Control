package com.ams.developer.api.expense.control.services.impl;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.UsuarioDto;
import com.ams.developer.api.expense.control.model.UsuariosModel;
import com.ams.developer.api.expense.control.repository.EstadosRepository;
import com.ams.developer.api.expense.control.repository.UsuarioRepository;
import com.ams.developer.api.expense.control.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadosRepository estadosRepository;

    @Override
    public UsuariosModel searchByCorreo(String correo) {
        return this.usuarioRepository.findByCorreo(correo);
    }

    @Override
    public UsuariosModel searchByCorreoActive(String correo) {
        Optional<UsuariosModel> usuario = this.usuarioRepository.findByCorreoAndEstadosId(correo, this.estadosRepository.findById("cc98363d-86c7-4576-a8da-ef256d337de3").orElse(null));
        if (usuario.isPresent()){
            return usuario.get();
        }
        return null;
    }

    @Override
    public ResponseEntity<ApiResponseDto> saveUser(UsuarioDto usuarioDto) {
        return null;
    }
}

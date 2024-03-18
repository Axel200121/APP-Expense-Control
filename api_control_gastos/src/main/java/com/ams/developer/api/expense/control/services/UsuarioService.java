package com.ams.developer.api.expense.control.services;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.UsuarioDto;
import com.ams.developer.api.expense.control.model.UsuariosModel;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {

    public UsuariosModel searchByCorreo(String correo);

    public UsuariosModel searchByCorreoActive(String correo);

    public ResponseEntity<ApiResponseDto> saveUser(UsuarioDto usuarioDto);
}
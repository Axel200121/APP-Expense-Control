package com.ams.developer.api.expense.control.services.impl;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.JwtResponseDto;
import com.ams.developer.api.expense.control.dto.LoginDto;
import com.ams.developer.api.expense.control.dto.UsuarioDto;
import com.ams.developer.api.expense.control.model.ProveedoresModel;
import com.ams.developer.api.expense.control.model.UsuariosModel;
import com.ams.developer.api.expense.control.repository.EstadosRepository;
import com.ams.developer.api.expense.control.repository.UsuarioRepository;
import com.ams.developer.api.expense.control.security.jwt.JwtService;
import com.ams.developer.api.expense.control.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadosRepository estadosRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Autowired
    private JwtService jwtService;

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

    @Override
    public ResponseEntity<ApiResponseDto> login(LoginDto loginDto) {
        try {
            UsuariosModel usuario = searchByCorreoActive(loginDto.getCorreo());
            if (usuario == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "El usuario con estas credenciales no esta ACTIVO"));
            }else {
                if (this.passwordEncode.matches(loginDto.getPassword(),usuario.getPassword())){
                    String token = this.jwtService.generateToken(usuario.getCorreo());
                    JwtResponseDto response = new JwtResponseDto(usuario.getId(),usuario.getNombre(),usuario.getPerfilId().getNombre(),usuario.getPerfilId().getId(),token);
                    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(), "Bienvenido al sistema",response));
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "Las credenciales ingresadas no son validos"));
                }
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> refreshToken(String id) {
        try {
            Optional<UsuariosModel> usuario = usuarioRepository.findById(id);
            if (usuario.isPresent()){
                UsuariosModel usuarioExistente = usuario.get();
                JwtResponseDto response = new JwtResponseDto(
                        usuarioExistente.getId(),usuarioExistente.getNombre(),usuarioExistente.getPerfilId().getNombre(),
                        usuarioExistente.getPerfilId().getId(), this.jwtService.generateToken(usuarioExistente.getCorreo())
                );
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(), "Token Actualizado",response));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "El usuario con estas credenciales no esta ACTIVO"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }
}

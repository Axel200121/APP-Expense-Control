package com.ams.developer.api.expense.control.services.impl;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.JwtResponseDto;
import com.ams.developer.api.expense.control.dto.LoginDto;
import com.ams.developer.api.expense.control.dto.UsuarioDto;
import com.ams.developer.api.expense.control.mapper.UsuarioMapper;
import com.ams.developer.api.expense.control.model.EstadosModel;
import com.ams.developer.api.expense.control.model.PerfilModel;
import com.ams.developer.api.expense.control.model.UsuariosModel;
import com.ams.developer.api.expense.control.repository.EstadosRepository;
import com.ams.developer.api.expense.control.repository.PerfilRepository;
import com.ams.developer.api.expense.control.repository.UsuarioRepository;
import com.ams.developer.api.expense.control.security.jwt.JwtService;
import com.ams.developer.api.expense.control.services.GastosFijosService;
import com.ams.developer.api.expense.control.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadosRepository estadosRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(GastosFijosService.class);


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

    @Override
    public ResponseEntity<ApiResponseDto> saveUser(UsuarioDto usuarioDto) {
        try {
            UsuariosModel user = searchByCorreo(usuarioDto.getCorreo());
            if (user != null){
                LOGGER.info("ENCONTRO EL CORREO INGRESADO EN LA BD");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "El correo que ingresate ya existe"));
            }
            PerfilModel perfil = this.perfilRepository.findById("723b8423-7758-4b2a-a3ec-fddd2668dcd8").orElse(null);
            EstadosModel estado = this.estadosRepository.findById("cc98363d-86c7-4576-a8da-ef256d337de3").orElse(null);
            if (perfil != null && estado != null){
                String paswwordEncode = this.passwordEncode.encode(usuarioDto.getPassword());
                usuarioDto.setPassword(paswwordEncode);
                usuarioDto.setFecha(new Date());
                usuarioDto.setToken(this.jwtService.generateToken(usuarioDto.getCorreo()));
                UsuariosModel usuariosModel = this.usuarioMapper.convertToEntity(usuarioDto,perfil,estado);
                this.usuarioRepository.save(usuariosModel);
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Usuario registrado",usuariosModel));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "Tu cuenta aun no esta activa"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> getAllUsers() {
        try {
            List<UsuariosModel> usuariosModelList = this.usuarioRepository.findAll();
            if (!usuariosModelList.isEmpty()){
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Lista de usuarios",usuariosModelList));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "NO hay usuarios registrado en la BD"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> getUserById(String id) {
        try {
            Optional<UsuariosModel> usuarioModel = this.usuarioRepository.findById(id);
            if (usuarioModel.isPresent()){
                UsuariosModel usuarioExistente = usuarioModel.get();
                UsuarioDto usuarioDto = this.usuarioMapper.convertToDto(usuarioExistente);
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Usuario encontrado",usuarioDto));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el usuario con el id ingresado"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> updateUser(String id, UsuarioDto usuarioDto) {
        return null;
    }
}

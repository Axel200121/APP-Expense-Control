package com.ams.developer.api.expense.control.mapper;

import com.ams.developer.api.expense.control.dto.*;
import com.ams.developer.api.expense.control.model.*;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuariosModel convertToEntity(UsuarioDto usuarioDto, PerfilModel perfilModel, EstadosModel estadosModel){
        UsuariosModel usuariosModel = new UsuariosModel();
        usuariosModel.setId(usuarioDto.getId());
        usuariosModel.setNombre(usuarioDto.getNombre());
        usuariosModel.setCorreo(usuarioDto.getCorreo());
        usuariosModel.setPassword(usuarioDto.getPassword());
        usuariosModel.setToken(usuarioDto.getToken());
        usuariosModel.setFecha(usuarioDto.getFecha());
        usuariosModel.setPerfilId(perfilModel);
        usuariosModel.setEstadosId(estadosModel);
        return usuariosModel;
    }

    public UsuarioDto convertToDto(UsuariosModel usuariosModel){
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuariosModel.getId());
        usuarioDto.setNombre(usuariosModel.getNombre());
        usuarioDto.setCorreo(usuariosModel.getCorreo());
        usuarioDto.setPassword(usuariosModel.getPassword());
        usuarioDto.setToken(usuariosModel.getToken());
        usuarioDto.setFecha(usuariosModel.getFecha());
        if (usuariosModel.getPerfilId() != null){
            PerfilModel perfilModel = usuariosModel.getPerfilId();
            PerfilDto perfilDto = new PerfilDto();
            perfilDto.setId(perfilModel.getId());
            perfilDto.setNombre(perfilModel.getNombre());
            usuarioDto.setPerfilDto(perfilDto); // Asignar el proveedorDto al gastoFijoDto
        }

        if (usuariosModel.getEstadosId() != null){
            EstadosModel estadosModel = usuariosModel.getEstadosId();
            EstadoDto estadoDto = new EstadoDto();
            estadoDto.setId(estadosModel.getId());
            estadoDto.setNombre(estadosModel.getNombre());
            usuarioDto.setEstadosDto(estadoDto);
        }
        return  usuarioDto;
    }
}

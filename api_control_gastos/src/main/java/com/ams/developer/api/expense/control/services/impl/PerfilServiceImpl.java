package com.ams.developer.api.expense.control.services.impl;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.PerfilDto;
import com.ams.developer.api.expense.control.dto.ProveedorDto;
import com.ams.developer.api.expense.control.mapper.PerfilMapper;
import com.ams.developer.api.expense.control.model.PerfilModel;
import com.ams.developer.api.expense.control.model.ProveedoresModel;
import com.ams.developer.api.expense.control.repository.PerfilRepository;
import com.ams.developer.api.expense.control.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PerfilMapper perfilMapper;

    @Override
    public ResponseEntity<ApiResponseDto> getAllPerfiles() {
        try {
            List<PerfilModel> listPerfiles = this.perfilRepository.findAll();
            if (!listPerfiles.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(),"Lista de perfiles",listPerfiles));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No hay registros en la BD"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> savePerfil(PerfilDto perfilDto) {
        try {
            if (perfilDto != null){
                PerfilModel perfilEntity = perfilMapper.convertToEntity(perfilDto);
                this.perfilRepository.save(perfilEntity);
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Perfil registrado",perfilEntity));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "El dto del perfil viene vacio"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> getPerfilById(String id) {
        try {
            PerfilModel perfilEntity = this.perfilRepository.findById(id).orElse(null);
            if (perfilEntity != null){
                PerfilDto perfilDto = perfilMapper.convertToDto(perfilEntity);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(),"Perfil encontrado",perfilDto));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el perfil con el id "+id));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> deletePerfil(String id) {
        try {
            Optional<PerfilModel> perfil = this.perfilRepository.findById(id);
            if (perfil.isPresent()){
                this.perfilRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.NO_CONTENT.value(),"Perfil elminado"));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el perfil con el id "+id));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> updatePerfil(String id, PerfilDto perfilDto) {
        try {
            Optional<PerfilModel> perfil = this.perfilRepository.findById(id);
            if (perfil.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el perfil con el id "+id));
            }
            PerfilModel perfilExistente = perfil.get();
            perfilExistente.setNombre(perfilDto.getNombre());
            this.perfilRepository.save(perfilExistente);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Perfil actualizado",perfilExistente));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }
}

package com.ams.developer.api.expense.control.services.impl;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.ProveedorDto;
import com.ams.developer.api.expense.control.mapper.ProveedorMapper;
import com.ams.developer.api.expense.control.model.ProveedoresModel;
import com.ams.developer.api.expense.control.repository.ProveedorRepository;
import com.ams.developer.api.expense.control.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorMapper proveedorMapper;

    @Override
    public ResponseEntity<ApiResponseDto> saveProveedor(ProveedorDto proveedorDto) {
        try {
            if (proveedorDto != null){
                ProveedoresModel proveedorModel = proveedorMapper.convertToEntity(proveedorDto);
                this.proveedorRepository.save(proveedorModel);
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Proveedor registrado",proveedorModel));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "El dto del proveedor viene vacio"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> getAllProveedores() {
        try {
            List<ProveedoresModel> listProveedores = this.proveedorRepository.findAll();
            if (!listProveedores.isEmpty()){
                List<ProveedorDto> proveedorDtoList = listProveedores.stream().map(proveedorMapper::convertToDto).toList();
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(),"Lista de proveedores",proveedorDtoList));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No hay registros en la BD"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> getProveedotById(String id) {
        try {
            ProveedoresModel proveedorEntity = this.proveedorRepository.findById(id).orElse(null);
            if (proveedorEntity != null){
                ProveedorDto proveedorDto = proveedorMapper.convertToDto(proveedorEntity);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(),"Proveedor encontrado",proveedorDto));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el proveedor con el id "+id));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> updateProveedor(String id, ProveedorDto proveedorDto) {
        try {
            Optional<ProveedoresModel> proveedor = this.proveedorRepository.findById(id);
            if (proveedor.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el proveedor con el id "+id));
            }
            ProveedoresModel proveedorExistente = proveedor.get();
            proveedorExistente.setNombre(proveedorDto.getNombre());
            this.proveedorRepository.save(proveedorExistente);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Proveedor actualizado",proveedorExistente));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> deleteProveedor(String id) {
        try {
            Optional<ProveedoresModel> proveedor = this.proveedorRepository.findById(id);
            if (proveedor.isPresent()){
                this.proveedorRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.NO_CONTENT.value(),"Proveedor elminado"));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el proveedor con el id "+id));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }
}

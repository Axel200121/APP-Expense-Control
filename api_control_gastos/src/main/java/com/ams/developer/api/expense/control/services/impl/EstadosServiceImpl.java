package com.ams.developer.api.expense.control.services.impl;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.EstadoDto;
import com.ams.developer.api.expense.control.mapper.EstadoMapper;
import com.ams.developer.api.expense.control.model.EstadosModel;
import com.ams.developer.api.expense.control.repository.EstadosRepository;
import com.ams.developer.api.expense.control.services.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstadosServiceImpl implements EstadosService {

    @Autowired
    private EstadosRepository estadosRepository;

    @Autowired
    private EstadoMapper estadoMapper;

    @Override
    public ResponseEntity<ApiResponseDto>  listar() {
        try {
            List<EstadosModel> estadosList =  this.estadosRepository.findAll();  //findAll(Sort.by("id").descending()--> ordenamos los registros de forma descendentes
            if (!estadosList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(),"Lista de estados registrados",estadosList));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No hay registros en la BD"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> saveEstado(EstadoDto estadoDto) {
        try {
           if (estadoDto.getNombre() != null){
               EstadosModel estadosModel = estadoMapper.convertToEntity(estadoDto);
               this.estadosRepository.save(estadosModel);
               return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Estado registrado",estadosModel));
           }else{
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "Campo nombre no viene"));
           }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> buscarporId(String id) {
        try {
            EstadosModel estadosModel = this.estadosRepository.findById(id).orElse(null);
            if (estadosModel != null){
                EstadoDto estadoDto = estadoMapper.convertToDto(estadosModel);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(),"Estado encontrado",estadoDto));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el estado con el id "+id));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> eliminar(String id) {
        try {
            Optional<EstadosModel> estadosModel = this.estadosRepository.findById(id);
            if (estadosModel.isPresent()){
                this.estadosRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.NO_CONTENT.value(),"Estado elminado"));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro estado con el id "+id));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor"));
        }
    }
}

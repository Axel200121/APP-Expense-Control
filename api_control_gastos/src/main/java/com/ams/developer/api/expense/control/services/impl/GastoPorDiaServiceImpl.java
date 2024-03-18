package com.ams.developer.api.expense.control.services.impl;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.GastoFijoDto;
import com.ams.developer.api.expense.control.dto.GastoPorDiaDto;
import com.ams.developer.api.expense.control.mapper.GastoPorDiaMapper;
import com.ams.developer.api.expense.control.model.EstadosModel;
import com.ams.developer.api.expense.control.model.GastosFijosModel;
import com.ams.developer.api.expense.control.model.GastosPorDiaModel;
import com.ams.developer.api.expense.control.model.ProveedoresModel;
import com.ams.developer.api.expense.control.repository.GastosPorDiaRepository;
import com.ams.developer.api.expense.control.repository.ProveedorRepository;
import com.ams.developer.api.expense.control.services.GastosPorDiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastoPorDiaServiceImpl implements GastosPorDiaService {
    @Autowired
    private GastosPorDiaRepository gastosPorDiaRepository;

    @Autowired
    private GastoPorDiaMapper gastoPorDiaMapper;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public ResponseEntity<ApiResponseDto> getAllGastosPorDia() {
        try {
            List<GastosPorDiaModel> gastosPorDialList = this.gastosPorDiaRepository.findAll();
            if (!gastosPorDialList.isEmpty()){
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Lista de gastos por días",gastosPorDialList));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No hay registros en la BD"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> getFilterGastosPorDiaByMes(Integer mes, Integer anio) {
        try {
            List<GastosPorDiaModel> gastosPorDiaFilterByMes = this.gastosPorDiaRepository.findAllByMonth(mes, anio);
            if (!gastosPorDiaFilterByMes.isEmpty()){
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Gastos por dia filtrados por mes y anio",gastosPorDiaFilterByMes));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro gastos por dia en ese periodo"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }    }

    @Override
    public ResponseEntity<ApiResponseDto> getGastoPorDia(String id) {
        try {
            GastosPorDiaModel gastoPorDiaEntity = this.gastosPorDiaRepository.findById(id).orElse(null);
            if (gastoPorDiaEntity != null){
                GastoPorDiaDto gastoPorDiaDto = gastoPorDiaMapper.convertToDto(gastoPorDiaEntity);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(),"Gasto por dia encontrado",gastoPorDiaDto));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el gasto por dia con el id "+id));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> saveGastoPorDia(GastoPorDiaDto gastoPorDiaDto) {
        try {
            ProveedoresModel proveedorEntity = proveedorRepository.findById(gastoPorDiaDto.getProveedorDto().getId()).orElse(null);
            if (proveedorEntity != null){
                GastosPorDiaModel gastosPorDiaModel = gastoPorDiaMapper.convertToEntity(gastoPorDiaDto,proveedorEntity);
                this.gastosPorDiaRepository.save(gastosPorDiaModel);
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Gasto por dia registrado",gastosPorDiaModel));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No encontro información del proveedor"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> deleteGastoPorDia(String id) {
        try {
            Optional<GastosPorDiaModel> gastoPorDiaModel = this.gastosPorDiaRepository.findById(id);
            if (gastoPorDiaModel.isPresent()){
                this.gastosPorDiaRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.NO_CONTENT.value(),"Gasto por día elminado"));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el gasto por dia con el id "+id));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> updateGastoPorDia(GastoPorDiaDto gastoPorDiaDto, String id) {
        try {
            Optional<GastosPorDiaModel> gastoPorDiaEntity = this.gastosPorDiaRepository.findById(id);
            if (gastoPorDiaEntity.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No encontro el id del gastofijo"));
            }
            GastosPorDiaModel gastoPorDiaExistente = gastoPorDiaEntity.get();
            ProveedoresModel proveedorEntity = proveedorRepository.findById(gastoPorDiaDto.getProveedorDto().getId()).orElse(null);
            if (proveedorEntity != null){
                gastoPorDiaExistente.setNeto(gastoPorDiaDto.getNeto());
                gastoPorDiaExistente.setIva(gastoPorDiaDto.getIva());
                gastoPorDiaExistente.setTotal(gastoPorDiaDto.getTotal());
                gastoPorDiaExistente.setFecha(gastoPorDiaDto.getFecha());
                gastoPorDiaExistente.setDescripcion(gastoPorDiaDto.getDescripcion());
                gastoPorDiaExistente.setProveedoresId(proveedorEntity);
                this.gastosPorDiaRepository.save(gastoPorDiaExistente);
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Gasto por día actualizado",gastoPorDiaExistente));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No encontro información del proveedor"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor---> ( " + e.getMessage()+" )"));
        }
    }
}

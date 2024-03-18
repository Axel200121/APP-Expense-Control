package com.ams.developer.api.expense.control.services.impl;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.GastoFijoDto;
import com.ams.developer.api.expense.control.mapper.GastosFijosMapper;
import com.ams.developer.api.expense.control.model.EstadosModel;
import com.ams.developer.api.expense.control.model.GastosFijosModel;
import com.ams.developer.api.expense.control.model.ProveedoresModel;
import com.ams.developer.api.expense.control.repository.EstadosRepository;
import com.ams.developer.api.expense.control.repository.GastosFijosRepository;
import com.ams.developer.api.expense.control.repository.ProveedorRepository;
import com.ams.developer.api.expense.control.services.GastosFijosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GastosFijosServiceImpl implements GastosFijosService {
    @Autowired
    private GastosFijosRepository gastosFijosRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private EstadosRepository estadosRepository;

    @Autowired
    private GastosFijosMapper gastosFijosMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(GastosFijosService.class);


    @Override
    public ResponseEntity<ApiResponseDto> getAllGastosFijos() {
        try {
            List<GastosFijosModel> gastosFijosModelList = this.gastosFijosRepository.findAll();
            if (!gastosFijosModelList.isEmpty()){
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Gasto Fijo registrado",gastosFijosModelList));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No hay registros en la BD"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> getFilterGastosFijosByMes(Integer mes, Integer anio) {
        try {
            List<GastosFijosModel> gastosFijosFilterByMes = this.gastosFijosRepository.findAllByMonth(mes, anio);
            if (!gastosFijosFilterByMes.isEmpty()){
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Gastos filtrados por mes y anio",gastosFijosFilterByMes));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro gastos fijos en ese periodo"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> getGastoFijo(String id) {
        try {
            GastosFijosModel gastoFijoEntity = this.gastosFijosRepository.findById(id).orElse(null);
            if (gastoFijoEntity != null){
                GastoFijoDto gastoFijoDto = gastosFijosMapper.convertToDto(gastoFijoEntity);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(),"Gasto fijo encontrado",gastoFijoDto));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el gasto fijo con el id "+id));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> saveGastoFijo(GastoFijoDto gastoFijoDto) {
        try {
            EstadosModel estadoEntity = estadosRepository.findById(gastoFijoDto.getEstadoDto().getId()).orElse(null);
            ProveedoresModel proveedorEntity = proveedorRepository.findById(gastoFijoDto.getProveedorDto().getId()).orElse(null);
            if (estadoEntity != null && proveedorEntity != null){
                GastosFijosModel gastosFijosModel = gastosFijosMapper.convertToEntity(gastoFijoDto,estadoEntity,proveedorEntity);
                this.gastosFijosRepository.save(gastosFijosModel);
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Gasto Fijo registrado",gastosFijosModel));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No encontro información del proveedor y estado"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> deleteGastoFijo(String id) {
        try {
            Optional<GastosFijosModel> gastoFijoModel = this.gastosFijosRepository.findById(id);
            if (gastoFijoModel.isPresent()){
                this.gastosFijosRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.NO_CONTENT.value(),"Gasto Fijo elminado"));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No se encontro el gasto fijo con el id "+id));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor"));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> updateGastoFijo(GastoFijoDto gastoFijoDto, String id) {
        try {
            Optional<GastosFijosModel> gastoFijoEntity = this.gastosFijosRepository.findById(id);
            if (gastoFijoEntity.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No encontro el id del gastofijo"));
            }
            GastosFijosModel gastoFijoExistente = gastoFijoEntity.get();
            EstadosModel estadoEntity = estadosRepository.findById(gastoFijoDto.getEstadoDto().getId()).orElse(null);
            ProveedoresModel proveedorEntity = proveedorRepository.findById(gastoFijoDto.getProveedorDto().getId()).orElse(null);
            if (estadoEntity != null && proveedorEntity != null){
                gastoFijoExistente.setNombre(gastoFijoDto.getNombre());
                gastoFijoExistente.setMonto(gastoFijoDto.getMonto());
                gastoFijoExistente.setFecha(gastoFijoDto.getFecha());
                gastoFijoExistente.setEstadosId(estadoEntity);
                gastoFijoExistente.setProveedoresId(proveedorEntity);
                this.gastosFijosRepository.save(gastoFijoExistente);
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(),"Gasto Fijo actulizado",gastoFijoExistente));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "No encontro información del proveedor y estado"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error interno del servidor"));
        }
    }


}

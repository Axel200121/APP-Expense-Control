package com.ams.developer.api.expense.control.mapper;

import com.ams.developer.api.expense.control.dto.EstadoDto;
import com.ams.developer.api.expense.control.dto.GastoFijoDto;
import com.ams.developer.api.expense.control.dto.ProveedorDto;
import com.ams.developer.api.expense.control.model.EstadosModel;
import com.ams.developer.api.expense.control.model.GastosFijosModel;
import com.ams.developer.api.expense.control.model.ProveedoresModel;
import org.springframework.stereotype.Component;

@Component
public class GastosFijosMapper {

    public GastosFijosModel convertToEntity(GastoFijoDto gastoFijoDto, EstadosModel estadosModel, ProveedoresModel proveedoresModel){
        GastosFijosModel gastosFijosModel = new GastosFijosModel();
        gastosFijosModel.setId(gastoFijoDto.getId());
        gastosFijosModel.setNombre(gastoFijoDto.getNombre());
        gastosFijosModel.setMonto(gastoFijoDto.getMonto());
        gastosFijosModel.setFecha(gastoFijoDto.getFecha());
        gastosFijosModel.setEstadosId(estadosModel);
        gastosFijosModel.setProveedoresId(proveedoresModel);
        return gastosFijosModel;
    }

    public GastoFijoDto convertToDto(GastosFijosModel gastosFijosModel){
        GastoFijoDto gastoFijoDto = new GastoFijoDto();
        gastoFijoDto.setId(gastosFijosModel.getId());
        gastoFijoDto.setNombre(gastosFijosModel.getNombre());
        gastoFijoDto.setMonto(gastosFijosModel.getMonto());
        gastoFijoDto.setFecha(gastosFijosModel.getFecha());
        if (gastosFijosModel.getEstadosId() != null){
            EstadosModel estadosModel = gastosFijosModel.getEstadosId();
            EstadoDto estadoDto = new EstadoDto();
            estadoDto.setId(estadosModel.getId());
            estadoDto.setNombre(estadosModel.getNombre());
            gastoFijoDto.setEstadoDto(estadoDto); // Asignar el estadoDto al gastoFijoDto
        }
        if (gastosFijosModel.getProveedoresId() != null){
            ProveedoresModel proveedoresModel = gastosFijosModel.getProveedoresId();
            ProveedorDto proveedorDto = new ProveedorDto();
            proveedorDto.setId(proveedoresModel.getId());
            proveedorDto.setNombre(proveedoresModel.getNombre());
            gastoFijoDto.setProveedorDto(proveedorDto); // Asignar el proveedorDto al gastoFijoDto
        }
        return gastoFijoDto;
    }
}

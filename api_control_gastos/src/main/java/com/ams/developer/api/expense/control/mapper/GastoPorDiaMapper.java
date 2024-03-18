package com.ams.developer.api.expense.control.mapper;

import com.ams.developer.api.expense.control.dto.EstadoDto;
import com.ams.developer.api.expense.control.dto.GastoFijoDto;
import com.ams.developer.api.expense.control.dto.GastoPorDiaDto;
import com.ams.developer.api.expense.control.dto.ProveedorDto;
import com.ams.developer.api.expense.control.model.EstadosModel;
import com.ams.developer.api.expense.control.model.GastosFijosModel;
import com.ams.developer.api.expense.control.model.GastosPorDiaModel;
import com.ams.developer.api.expense.control.model.ProveedoresModel;
import org.springframework.stereotype.Component;

@Component
public class GastoPorDiaMapper {
    public GastosPorDiaModel convertToEntity(GastoPorDiaDto gastoPorDiaDto, ProveedoresModel proveedoresModel){
        GastosPorDiaModel gastosPorDiaModel = new GastosPorDiaModel();
        gastosPorDiaModel.setId(gastoPorDiaDto.getId());
        gastosPorDiaModel.setNeto(gastoPorDiaDto.getNeto());
        gastosPorDiaModel.setIva(gastoPorDiaDto.getIva());
        gastosPorDiaModel.setTotal(gastoPorDiaDto.getTotal());
        gastosPorDiaModel.setFecha(gastoPorDiaDto.getFecha());
        gastosPorDiaModel.setDescripcion(gastoPorDiaDto.getDescripcion());
        gastosPorDiaModel.setProveedoresId(proveedoresModel);
        return gastosPorDiaModel;
    }

    public GastoPorDiaDto convertToDto(GastosPorDiaModel gastosPorDiaModel){
        GastoPorDiaDto gastoPorDiaDto = new GastoPorDiaDto();
        gastoPorDiaDto.setId(gastosPorDiaModel.getId());
        gastoPorDiaDto.setNeto(gastosPorDiaModel.getNeto());
        gastoPorDiaDto.setIva(gastosPorDiaModel.getIva());
        gastoPorDiaDto.setTotal(gastosPorDiaModel.getTotal());
        gastoPorDiaDto.setFecha(gastosPorDiaModel.getFecha());
        gastoPorDiaDto.setDescripcion(gastosPorDiaModel.getDescripcion());
        if (gastosPorDiaModel.getProveedoresId() != null){
            ProveedoresModel proveedoresModel = gastosPorDiaModel.getProveedoresId();
            ProveedorDto proveedorDto = new ProveedorDto();
            proveedorDto.setId(proveedoresModel.getId());
            proveedorDto.setNombre(proveedoresModel.getNombre());
            gastoPorDiaDto.setProveedorDto(proveedorDto); // Asignar el proveedorDto al gastoFijoDto
        }
        return gastoPorDiaDto;
    }
}

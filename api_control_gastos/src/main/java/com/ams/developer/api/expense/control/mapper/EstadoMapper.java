package com.ams.developer.api.expense.control.mapper;

import com.ams.developer.api.expense.control.dto.EstadoDto;
import com.ams.developer.api.expense.control.model.EstadosModel;
import org.springframework.stereotype.Component;

@Component
public class EstadoMapper {

    public EstadosModel convertToEntity(EstadoDto estadoDto){
        EstadosModel estadosModel = new EstadosModel();
        estadosModel.setNombre(estadoDto.getNombre());
        return estadosModel;
    }

    public EstadoDto convertToDto(EstadosModel estadosModel){
        EstadoDto estadoDto = new EstadoDto();
        estadoDto.setId(estadosModel.getId());
        estadoDto.setNombre(estadosModel.getNombre());
        return estadoDto;
    }
}

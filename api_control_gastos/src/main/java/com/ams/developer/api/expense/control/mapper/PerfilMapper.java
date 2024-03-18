package com.ams.developer.api.expense.control.mapper;

import com.ams.developer.api.expense.control.dto.EstadoDto;
import com.ams.developer.api.expense.control.dto.PerfilDto;
import com.ams.developer.api.expense.control.model.EstadosModel;
import com.ams.developer.api.expense.control.model.PerfilModel;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {

    public PerfilModel convertToEntity(PerfilDto perfilDto){
        PerfilModel perfilModel = new PerfilModel();
        perfilModel.setId(perfilDto.getId());
        perfilModel.setNombre(perfilDto.getNombre());
        return perfilModel;
    }

    public PerfilDto convertToDto(PerfilModel perfilModel ){
        PerfilDto perfilDto = new PerfilDto();
        perfilDto.setId(perfilModel.getId());
        perfilDto.setNombre(perfilModel.getNombre());
        return perfilDto;
    }

}

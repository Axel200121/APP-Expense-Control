package com.ams.developer.api.expense.control.mapper;


import com.ams.developer.api.expense.control.dto.ProveedorDto;
import com.ams.developer.api.expense.control.model.ProveedoresModel;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public ProveedoresModel convertToEntity(ProveedorDto proveedorDto){
        ProveedoresModel proveedoresModel = new ProveedoresModel();
        proveedoresModel.setId(proveedorDto.getId());
        proveedoresModel.setNombre(proveedorDto.getNombre());
        return proveedoresModel;
    }

    public ProveedorDto convertToDto(ProveedoresModel proveedoresModel){
        ProveedorDto proveedorDto = new ProveedorDto();
        proveedorDto.setId(proveedoresModel.getId());
        proveedorDto.setNombre(proveedoresModel.getNombre());
        return proveedorDto;
    }
}

package com.ams.developer.api.expense.control.dto;

import com.ams.developer.api.expense.control.model.EstadosModel;
import com.ams.developer.api.expense.control.model.ProveedoresModel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class GastoFijoDto {
    private String id;
    private String nombre;
    private Long monto;
    private Date fecha;
    private EstadoDto estadoDto;
    private ProveedorDto proveedorDto;
}

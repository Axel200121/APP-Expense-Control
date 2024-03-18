package com.ams.developer.api.expense.control.dto;

import com.ams.developer.api.expense.control.model.ProveedoresModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class GastoPorDiaDto {
    private String id;
    private Long neto;
    private Long iva;
    private Long total;
    private Date fecha;
    private String descripcion;
    private ProveedorDto proveedorDto;
}

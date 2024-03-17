package com.ams.developer.api.expense.control.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "gastos_por_dia" )
@Data
public class GastosPorDiaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long neto;
    private Long iva;
    private Long total;
    private Date fecha;
    @Column(length = 65535, columnDefinition = "text")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "proveedores_id")
    private ProveedoresModel proveedoresId;

    public GastosPorDiaModel(Long neto, Long iva, Long total, Date fecha, String descripcion, ProveedoresModel proveedoresId) {
        super();
        this.neto = neto;
        this.iva = iva;
        this.total = total;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.proveedoresId = proveedoresId;
    }

    public GastosPorDiaModel() {
        super();
    }
}

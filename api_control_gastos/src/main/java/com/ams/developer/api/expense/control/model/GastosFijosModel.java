package com.ams.developer.api.expense.control.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "gastos_fijos")
@Data
public class GastosFijosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nombre;
    private Long monto;
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "estados_id")
    private  EstadosModel estadosId;

    @ManyToOne
    @JoinColumn(name = "proveedores_id")
    private  ProveedoresModel proveedoresId;

    public GastosFijosModel(String nombre, Long monto, Date fecha, EstadosModel estadosId, ProveedoresModel proveedoresId) {
        super();
        this.nombre = nombre;
        this.monto = monto;
        this.fecha = fecha;
        this.estadosId = estadosId;
        this.proveedoresId = proveedoresId;
    }

    public GastosFijosModel() {
        super();
    }
}

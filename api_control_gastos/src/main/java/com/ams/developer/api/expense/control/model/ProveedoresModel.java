package com.ams.developer.api.expense.control.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "proveedores")
@Data
public class ProveedoresModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nombre;

    public ProveedoresModel(String nombre) {
        super();
        this.nombre = nombre;
    }

    public ProveedoresModel() {
        super();
    }
}

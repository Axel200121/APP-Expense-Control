package com.ams.developer.api.expense.control.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estados")
@Data
public class EstadosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    private String nombre;
}

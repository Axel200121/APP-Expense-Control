package com.ams.developer.api.expense.control.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "variables_globales")
@Data //genera de fomra abstracta los getters y setters y construtor
public class VariablesGlobalesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nombre;
    private String valor;
}

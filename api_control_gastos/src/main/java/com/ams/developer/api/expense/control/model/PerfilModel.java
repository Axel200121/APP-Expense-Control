package com.ams.developer.api.expense.control.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "perfil")
@Data
public class PerfilModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nombre;

}

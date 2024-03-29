package com.ams.developer.api.expense.control.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "usuarios")
@Data
public class UsuariosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nombre;
    private String correo;
    private String password;
    private String token;
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "perfil_id") // se usa para darle un nombre al campo
    private PerfilModel perfilId;

    @ManyToOne
    @JoinColumn(name = "estados_id") // se usa para darle un nombre al campo
    private EstadosModel estadosId;

    public UsuariosModel(String nombre, String correo, String password, String token, Date fecha, PerfilModel perfilId, EstadosModel estadosId) {
        super();
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.token = token;
        this.fecha = fecha;
        this.perfilId = perfilId;
        this.estadosId = estadosId;
    }

    public UsuariosModel() {
        super();
    }
}

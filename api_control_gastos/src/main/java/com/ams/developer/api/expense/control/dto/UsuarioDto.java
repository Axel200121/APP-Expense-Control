package com.ams.developer.api.expense.control.dto;
import lombok.Data;

import java.util.Date;

@Data
public class UsuarioDto {
    private String id;
    private String nombre;
    private String correo;
    private String password;
    private String token;
    private Date fecha;
    private PerfilDto perfilDto;
    private EstadoDto estadosDto;
}

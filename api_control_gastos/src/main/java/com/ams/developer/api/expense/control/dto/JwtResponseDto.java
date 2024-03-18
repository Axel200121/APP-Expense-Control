package com.ams.developer.api.expense.control.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponseDto {
    private String id;
    private String nombre;
    private String perfil;
    private String perfil_id;
    private String token;
}

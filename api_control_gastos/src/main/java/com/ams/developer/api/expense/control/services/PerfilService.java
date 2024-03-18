package com.ams.developer.api.expense.control.services;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.EstadoDto;
import com.ams.developer.api.expense.control.dto.PerfilDto;
import org.springframework.http.ResponseEntity;

public interface PerfilService {
    public ResponseEntity<ApiResponseDto> getAllPerfiles();

    public ResponseEntity<ApiResponseDto> savePerfil(PerfilDto perfilDto);

    public ResponseEntity<ApiResponseDto> getPerfilById(String id);

    public ResponseEntity<ApiResponseDto> deletePerfil(String id);

    public ResponseEntity<ApiResponseDto> updatePerfil(String id, PerfilDto perfilDto);
}

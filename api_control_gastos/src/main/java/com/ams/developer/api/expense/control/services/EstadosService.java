package com.ams.developer.api.expense.control.services;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.EstadoDto;
import com.ams.developer.api.expense.control.model.EstadosModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstadosService {

    public ResponseEntity<ApiResponseDto> listar();
    public ResponseEntity<ApiResponseDto> saveEstado(EstadoDto estadoDto);
    public ResponseEntity<ApiResponseDto> buscarporId(String id);
    public ResponseEntity<ApiResponseDto> eliminar(String id);

}

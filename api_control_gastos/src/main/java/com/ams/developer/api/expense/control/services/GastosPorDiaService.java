package com.ams.developer.api.expense.control.services;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.GastoFijoDto;
import com.ams.developer.api.expense.control.dto.GastoPorDiaDto;
import org.springframework.http.ResponseEntity;

public interface GastosPorDiaService {

    public ResponseEntity<ApiResponseDto> getAllGastosPorDia();

    public ResponseEntity<ApiResponseDto> getFilterGastosPorDiaByMes(Integer mes, Integer anio);

    public ResponseEntity<ApiResponseDto> getGastoPorDia(String id);

    public ResponseEntity<ApiResponseDto> saveGastoPorDia(GastoPorDiaDto gastoPorDiaDto);

    public ResponseEntity<ApiResponseDto> deleteGastoPorDia(String id);

    public ResponseEntity<ApiResponseDto> updateGastoPorDia(GastoPorDiaDto gastoPorDiaDto, String id);
}

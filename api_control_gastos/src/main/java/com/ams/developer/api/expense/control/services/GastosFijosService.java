package com.ams.developer.api.expense.control.services;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.GastoFijoDto;
import org.springframework.http.ResponseEntity;

public interface GastosFijosService {

    public ResponseEntity<ApiResponseDto>  getAllGastosFijos();

    public ResponseEntity<ApiResponseDto> getFilterGastosFijosByMes(Integer mes, Integer anio);

    public ResponseEntity<ApiResponseDto> getGastoFijo(String id);

    public ResponseEntity<ApiResponseDto> saveGastoFijo(GastoFijoDto gastoFijoDto);

    public ResponseEntity<ApiResponseDto> deleteGastoFijo(String id);

    public ResponseEntity<ApiResponseDto> updateGastoFijo(GastoFijoDto gastoFijoDto, String id);

}

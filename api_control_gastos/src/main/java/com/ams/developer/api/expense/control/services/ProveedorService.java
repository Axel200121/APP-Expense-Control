package com.ams.developer.api.expense.control.services;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.ProveedorDto;
import org.springframework.http.ResponseEntity;

public interface ProveedorService {

    public ResponseEntity<ApiResponseDto> saveProveedor(ProveedorDto proveedorDto);

    public ResponseEntity<ApiResponseDto> getAllProveedores();

    public ResponseEntity<ApiResponseDto> getProveedotById(String id);

    public ResponseEntity<ApiResponseDto> updateProveedor(String id, ProveedorDto proveedorDto);

    public ResponseEntity<ApiResponseDto> deleteProveedor(String id);
}

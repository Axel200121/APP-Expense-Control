package com.ams.developer.api.expense.control.controller;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.ProveedorDto;
import com.ams.developer.api.expense.control.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> saveProveedor(@RequestBody ProveedorDto proveedorDto){
        return this.proveedorService.saveProveedor(proveedorDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseDto> getAllProveedores(){
        return this.proveedorService.getAllProveedores();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponseDto> getProveedorById(@PathVariable String id){
        return this.proveedorService.getProveedotById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto> updateProveedot(@PathVariable String id,  @RequestBody ProveedorDto proveedorDto){
        return this.proveedorService.updateProveedor(id, proveedorDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto> deleteProveedor(@PathVariable String id){
        return this.proveedorService.deleteProveedor(id);
    }
}

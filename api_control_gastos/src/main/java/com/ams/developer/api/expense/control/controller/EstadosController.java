package com.ams.developer.api.expense.control.controller;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.EstadoDto;
import com.ams.developer.api.expense.control.services.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/estados")
public class EstadosController {

    @Autowired
    private EstadosService estadosService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> saveEstado(@RequestBody EstadoDto estadoDto){
        return this.estadosService.saveEstado(estadoDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseDto> getAllEstados(){
        return this.estadosService.listar();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponseDto> getEstado(@PathVariable String id){
        return this.estadosService.buscarporId(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto> deleteEstado(@PathVariable String id){
        return this.estadosService.eliminar(id);
    }

}

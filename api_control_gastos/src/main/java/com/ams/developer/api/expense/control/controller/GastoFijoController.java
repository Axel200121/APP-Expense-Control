package com.ams.developer.api.expense.control.controller;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.GastoFijoDto;
import com.ams.developer.api.expense.control.services.GastosFijosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/gastos-fijos")
public class GastoFijoController {

    @Autowired
    private GastosFijosService gastosFijosService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> saveGastoFijo(@RequestBody GastoFijoDto gastoFijoDto){
        return this.gastosFijosService.saveGastoFijo(gastoFijoDto);
    }

    @GetMapping("/get-alls")
    public ResponseEntity<ApiResponseDto> getAllGastosFijos(){
        return this.gastosFijosService.getAllGastosFijos();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponseDto> getGastoFijo(@PathVariable String id){
        return this.gastosFijosService.getGastoFijo(id);
    }

    @GetMapping("/filter-by-mes/{mes}")
    public ResponseEntity<ApiResponseDto> getFilterByMes(@PathVariable Integer mes){
        LocalDate actualDate = LocalDate.now();
        return this.gastosFijosService.getFilterGastosFijosByMes(mes,actualDate.getYear());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto> deleteGastoFijo(@PathVariable String id){
        return this.gastosFijosService.deleteGastoFijo(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto> updateGastoFijo(@PathVariable String id, @RequestBody GastoFijoDto gastoFijoDto){
        return this.gastosFijosService.updateGastoFijo(gastoFijoDto,id);
    }
}

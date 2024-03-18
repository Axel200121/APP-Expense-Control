package com.ams.developer.api.expense.control.controller;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.GastoFijoDto;
import com.ams.developer.api.expense.control.dto.GastoPorDiaDto;
import com.ams.developer.api.expense.control.services.GastosPorDiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/gastos-por-dia")
public class GastoPorDiaController {
    @Autowired
    private GastosPorDiaService gastosPorDiaService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> saveGastoFijo(@RequestBody GastoPorDiaDto gastoPorDiaDto){
        return this.gastosPorDiaService.saveGastoPorDia(gastoPorDiaDto);
    }

    @GetMapping("/get-alls")
    public ResponseEntity<ApiResponseDto> getAllGastosPorDia(){
        return this.gastosPorDiaService.getAllGastosPorDia();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponseDto> getGastoPorDia(@PathVariable String id){
        return this.gastosPorDiaService.getGastoPorDia(id);
    }

    @GetMapping("/filter-by-mes/{mes}")
    public ResponseEntity<ApiResponseDto> getFilterByMes(@PathVariable Integer mes){
        LocalDate actualDate = LocalDate.now();
        return this.gastosPorDiaService.getFilterGastosPorDiaByMes(mes,actualDate.getYear());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto> deleteGastoPorDia(@PathVariable String id){
        return this.gastosPorDiaService.deleteGastoPorDia(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto> updateGastoProDia(@PathVariable String id, @RequestBody GastoPorDiaDto gastoPorDiaDto){
        return this.gastosPorDiaService.updateGastoPorDia(gastoPorDiaDto,id);
    }
}

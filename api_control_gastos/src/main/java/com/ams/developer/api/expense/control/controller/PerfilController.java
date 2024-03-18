package com.ams.developer.api.expense.control.controller;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.PerfilDto;
import com.ams.developer.api.expense.control.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> savePerfil(@RequestBody PerfilDto perfilDto){
        return this.perfilService.savePerfil(perfilDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseDto> getAllPerfil(){
        return this.perfilService.getAllPerfiles();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponseDto> getPerfilById(@PathVariable String id){
        return this.perfilService.getPerfilById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto> updatePerfil(@PathVariable String id,  @RequestBody PerfilDto perfilDto){
        return this.perfilService.updatePerfil(id,perfilDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto> deletePerfil(@PathVariable String id){
        return this.perfilService.deletePerfil(id);
    }

}

package com.ams.developer.api.expense.control.controller;
import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseDto> getAllUsers(){
        return this.usuarioService.getAllUsers();
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<ApiResponseDto> getUserById(@PathVariable String id){
        return this.usuarioService.getUserById(id);
    }
}

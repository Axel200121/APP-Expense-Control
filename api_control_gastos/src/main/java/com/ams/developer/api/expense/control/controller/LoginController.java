package com.ams.developer.api.expense.control.controller;

import com.ams.developer.api.expense.control.dto.ApiResponseDto;
import com.ams.developer.api.expense.control.dto.LoginDto;
import com.ams.developer.api.expense.control.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponseDto>Login(@RequestBody LoginDto loginDto){
        return this.usuarioService.login(loginDto);
    }

    @GetMapping("/auth/token/refresh/{id}")
    public ResponseEntity<ApiResponseDto> refreshToken(@PathVariable String id){
        return this.usuarioService.refreshToken(id);
    }

}

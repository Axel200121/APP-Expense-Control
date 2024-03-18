package com.ams.developer.api.expense.control.security.jwt;

import com.ams.developer.api.expense.control.model.UsuariosModel;
import com.ams.developer.api.expense.control.services.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Validamos el token que sea valido con la configuración que nosotros nesecitamos
        String authHeader = request.getHeader("Authorization"); //obtenemos el encabezado del token
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7); //pasamos el token
            username = jwtService.extractUsername(token); // recuperamos el correo del token
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UsuariosModel userDetail = this.usuarioService.searchByCorreo(username); //recuperamos la info del usuario
            if (this.jwtService.validateToken(token,userDetail)){ // si token es correcto o no
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetail,null,null);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}

package com.ams.developer.api.expense.control.security.jwt;

import com.ams.developer.api.expense.control.model.UsuariosModel;
import com.ams.developer.api.expense.control.repository.VariablesGlobalesRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtService {
    @Autowired
    private VariablesGlobalesRepository variablesGlobalesRepository;

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>(); //claims -> Son Objetos que permite compáritr informcación de forma segura
        return createToken(claims,username);
    }

    private String createToken(Map<String,Object> claims, String username){
        return Jwts.builder() //contruimos el token con builder
                .setClaims(claims) // pasamos lo claims, representa los payload
                .setSubject(username)// es una notación que se usa por estandar para creación del jwt, le pasamos el correo
                .setIssuedAt(new Date(System.currentTimeMillis())) // le pasamos fecha de creación del token
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 6000 * 30)) // fecha de expiración del token
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();// este para decir que tipo de de algoritmos vamos a ocupar
    }

    private Key getSignKey(){
        //buscamos el valor de la firma y la encriptamos
        byte[] keyBytes = Decoders.BASE64.decode("9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d99a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) { // sirve para estraer el username del token
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) { // sirve  para poder extraer la fecha de expiración
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) { // extraer todos los valores del token
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) { // permite poder validar la expieración del token
        return extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String token, UsuariosModel userDetails) { // nos va permitir porder validar el token
        final String username = extractUsername(token);
        return (username.equals(userDetails.getCorreo()) && !isTokenExpired(token));
    }

}

package com.uv.psw.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    /**
     * Constructor que inicializa la clave secreta desde application.properties.
     * @param secretKeyBase64 Clave secreta en formato Base64.
     */
    public JwtUtil(@Value("${jwt.secret.key}") String secretKeyBase64) {
        if (secretKeyBase64 == null) {
            throw new IllegalArgumentException("La clave secreta no está configurada en application.properties.");
        }
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyBase64);
        this.secretKey = Keys.hmacShaKeyFor(decodedKey);
    }

    /**
     * Genera un token JWT con el correo como sujeto.
     * @param correo El correo del usuario.
     * @return El token JWT.
     */
    public String generateToken(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)) // Expira en 2 horas
                .signWith(secretKey)
                .compact();
    }

    /**
     * Extrae el correo del token JWT.
     * @param token El token JWT.
     * @return El correo contenido en el token.
     */
    public String extractCorreo(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Valida si el token es válido para el correo proporcionado.
     * @param token El token JWT.
     * @param correo El correo que debe coincidir con el sujeto del token.
     * @return Verdadero si el token es válido, falso en caso contrario.
     */
    public boolean isTokenValid(String token, String correo) {
        return correo.equals(extractCorreo(token)) && !isTokenExpired(token);
    }

    /**
     * Verifica si el token JWT ha expirado.
     * @param token El token JWT.
     * @return Verdadero si el token ha expirado, falso en caso contrario.
     */
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    /**
     * Obtiene los claims del token JWT.
     * @param token El token JWT.
     * @return Los claims contenidos en el token.
     */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

package com.wow.libre.domain.shared.jwt;

import com.wow.libre.domain.ports.in.jwt.JwtPort;
import com.wow.libre.infrastructure.conf.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider implements JwtPort {

  private final JwtProperties jwtProperties;

  public JwtTokenProvider(JwtProperties jwtProperties) {
    this.jwtProperties = jwtProperties;
  }

  @Override
  public String extractAccountId(String token) {
    final Claims claims = extractAllClaims(token);
    return (String) claims.get("account_id");
  }

  @Override
  public Collection<GrantedAuthority> extractRoles(String token) {
    Claims claims = extractAllClaims(token);

    Collection<Map<String, String>> rolesAsMap = Optional.ofNullable(claims.get("roles"))
            .filter(Collection.class::isInstance)
            .map(Collection.class::cast)
            .orElse(Collections.emptyList());

    // Convertir los roles a SimpleGrantedAuthority
    List<GrantedAuthority> authorities = rolesAsMap.stream()
            .map(roleMap -> new SimpleGrantedAuthority(roleMap.get("authority")))
            .collect(Collectors.toList());
    return authorities;

  }

  @Override
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }


  @Override
  public boolean isTokenValid(String token) {
    return isSignatureValid(token) && !isTokenExpired(token);
  }

  private boolean isSignatureValid(String token) {
    try {
      extractAllClaims(token);
      return true;
    } catch (Exception e) {
      log.error("Error al validar la firma del token: {}", e.getMessage());
      return false; // La firma no es válida
    }
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  @Override
  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
            .parser()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getPayload();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
    return Keys.hmacShaKeyFor(keyBytes);
  }

}

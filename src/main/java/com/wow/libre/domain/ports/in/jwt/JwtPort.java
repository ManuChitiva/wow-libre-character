package com.wow.libre.domain.ports.in.jwt;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface JwtPort {
  String extractUsername(String token);

  Integer extractAccountId(String token);

  boolean isTokenValid(String token);

  Date extractExpiration(String token);

  Collection<GrantedAuthority>  extractRoles(String token);

}

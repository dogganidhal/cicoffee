package com.ndogga.cicoffe.service.impl;

import com.ndogga.cicoffe.configuration.properties.JwtConfigurationProperties;
import com.ndogga.cicoffe.model.entity.Member;
import com.ndogga.cicoffe.model.entity.RefreshToken;
import com.ndogga.cicoffe.model.repository.MemberRepository;
import com.ndogga.cicoffe.model.repository.RefreshTokenRepository;
import com.ndogga.cicoffe.service.AuthService;
import com.ndogga.cicoffe.web.dto.input.LoginDto;
import com.ndogga.cicoffe.web.dto.output.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 11:02 PM
 * Prize & Fun™ All rights reserved.
 */


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final MemberRepository memberRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtConfigurationProperties jwtConfiguration;

  @Override
  public TokenDto login(LoginDto request) {
    Member member = memberRepository
            .findByEmail(request.getEmail())
            .orElseThrow(IllegalArgumentException::new);
    if (!passwordEncoder.matches(request.getPassword(), member.getPasswordHash())) {
      throw new IllegalArgumentException();
    }
    return generateToken(member.getId());
  }

  @Override
  public TokenDto refresh(String token) {
    refreshTokenRepository
            .findById(token)
            .orElseThrow(IllegalStateException::new);
    Jws<Claims> claims = parseJwt(token);
    if (claims == null) {
      throw new IllegalArgumentException();
    }
    return generateToken(UUID.fromString(claims.getBody().getSubject()));
  }

  private TokenDto generateToken(UUID memberId) {
    String accessToken = issueJwt(memberId, getExpirationDate(jwtConfiguration.getExpiresIn()));
    String refreshToken = getOrCreateRefreshToken(memberId);
    return TokenDto.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .expiresIn(jwtConfiguration.getExpiresIn())
            .type("Bearer")
            .build();
  }

  private String issueJwt(UUID memberId, Date expiration) {
    return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(jwtConfiguration.getSigningKey().getBytes()))
            .setSubject(memberId.toString())
            .setExpiration(expiration)
            .compact();
  }

  private Date getExpirationDate(Long expiresInSeconds) {
    Instant instant = LocalDateTime.now()
            .plusSeconds(expiresInSeconds)
            .atZone(ZoneId.systemDefault())
            .toInstant();
    return Date.from(instant);
  }

  private String getOrCreateRefreshToken(UUID memberId) {
    Date expiration = getExpirationDate(jwtConfiguration.getRefreshTokenExpiresIn() * 3600 * 24);
    Optional<RefreshToken> refreshToken = refreshTokenRepository
            .findAllByMemberId(memberId)
            .stream()
            .filter(token -> parseJwt(token.getToken()) != null)
            .findAny();
    if (refreshToken.isEmpty()) {
      refreshToken = Optional.of(refreshTokenRepository.save(
              RefreshToken.builder()
                      .token(issueJwt(memberId, expiration))
                      .member(memberRepository.getOne(memberId))
                      .build()
      ));
    }
    return refreshToken.get().getToken();
  }

  private Jws<Claims> parseJwt(String jwt) {
    JwtParser jwtParser = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(jwtConfiguration.getSigningKey().getBytes()))
            .build();
    return jwtParser.parseClaimsJws(jwt);
  }

}

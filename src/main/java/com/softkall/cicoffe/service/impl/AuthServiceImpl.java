package com.softkall.cicoffe.service.impl;

import com.softkall.cicoffe.configuration.properties.JwtConfigurationProperties;
import com.softkall.cicoffe.configuration.properties.WebConfigurationProperties;
import com.softkall.cicoffe.exception.InvalidCredentialsException;
import com.softkall.cicoffe.exception.NotFoundException;
import com.softkall.cicoffe.model.entity.Member;
import com.softkall.cicoffe.model.entity.RefreshToken;
import com.softkall.cicoffe.model.repository.MemberRepository;
import com.softkall.cicoffe.model.repository.RefreshTokenRepository;
import com.softkall.cicoffe.service.AuthService;
import com.softkall.cicoffe.web.dto.input.LoginDto;
import com.softkall.cicoffe.web.dto.output.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.softkall.cicoffe.service.MailService;
import com.softkall.cicoffe.model.entity.ResetCode;
import com.softkall.cicoffe.model.repository.ResetCodeRepository;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 11:02 PM
 * SoftKall™ All rights reserved.
 */


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final MemberRepository memberRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final MailService mailService;
  private final JwtConfigurationProperties jwtConfiguration;
  private final WebConfigurationProperties webConfiguration;
  private final ResetCodeRepository resetCodeRepository;

  @Override
  public UUID decodeJwt(String token) {
    return parseJwt(token)
            .map(jwt -> UUID.fromString(jwt.getBody().getSubject()))
            .orElse(null);
  }

  @Override
  public TokenDto login(LoginDto request) {
    Member member = memberRepository
            .findByEmail(request.getEmail().toLowerCase())
            .orElseThrow(NotFoundException::new);
    if (!passwordEncoder.matches(request.getPassword(), member.getPasswordHash())) {
      throw new InvalidCredentialsException();
    }

    return generateToken(member.getId());
  }

  @Override
  public TokenDto refresh(String token) {
    refreshTokenRepository
            .findById(token)
            .orElseThrow(NotFoundException::new);
    Jws<Claims> claims = parseJwt(token)
            .orElseThrow(InvalidCredentialsException::new);
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
            .filter(token -> parseJwt(token.getToken()).isPresent())
            .findAny();
    if (refreshToken.isEmpty()) {
      refreshToken = Optional.of(refreshTokenRepository.save(
              RefreshToken.builder()
                      .token(issueJwt(memberId, expiration))
                      .member(memberRepository.getById(memberId))
                      .build()
      ));
    }
    return refreshToken.get().getToken();
  }

  private Optional<Jws<Claims>> parseJwt(String jwt) {
    try {
      JwtParser jwtParser = Jwts.parserBuilder()
              .setSigningKey(Keys.hmacShaKeyFor(jwtConfiguration.getSigningKey().getBytes()))
              .build();
      return Optional.of(jwtParser.parseClaimsJws(jwt));
    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException throwable) {
      return Optional.empty();
    }
  }

  @Override
  public void forgotPassword(String email){

    Member member = memberRepository
            .findByEmail(email.toLowerCase())
            .orElseThrow(NotFoundException::new);

    ResetCode resetCode = resetCodeRepository.save(ResetCode.builder()
            .code(getRandomHexString())
            .createdDate(LocalDateTime.now())
            .member(member)
            .build()
    );

    String resetUrl = String.format(
            "%s/content/reset-password?code=%s",
            webConfiguration.getServerUrl(),
            resetCode.getCode()
    );
    mailService.sendResetPassword(member.getEmail(), resetUrl);
  }

  @Override
  public void resetPassword(Member member, String password) {
      memberRepository.save(member.withPasswordHash(passwordEncoder.encode(password)));
  }

  private String getRandomHexString() {
    long stringLength = 36;
    ThreadLocalRandom random = ThreadLocalRandom.current();
    StringBuilder stringBuffer = new StringBuilder();
    while(stringBuffer.length() < stringLength){
      stringBuffer.append(Integer.toHexString(random.nextInt()));
    }
    return stringBuffer.substring(0, (int) stringLength);
  }

}

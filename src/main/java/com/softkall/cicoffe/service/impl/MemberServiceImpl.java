package com.softkall.cicoffe.service.impl;

import com.softkall.cicoffe.model.entity.Member;
import com.softkall.cicoffe.model.repository.MemberRepository;
import com.softkall.cicoffe.service.MemberService;
import com.softkall.cicoffe.web.dto.input.CreateMemberDto;
import com.softkall.cicoffe.web.dto.output.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @since 11/13/2020 10:48 PM
 * SoftKall™ All rights reserved.
 */


@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public MemberDto signUp(CreateMemberDto request) {
    Member member = memberRepository.save(Member.builder()
            .email(request.getEmail())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .passwordHash(passwordEncoder.encode(request.getPassword()))
            .build()
    );
    return MemberDto.builder()
            .id(member.getId())
            .email(member.getEmail())
            .firstName(member.getFirstName())
            .lastName(member.getLastName())
            .build();
  }

  @Override
  public MemberDto me(UUID memberId) {
    Member member = memberRepository
            .findById(memberId)
            .orElseThrow(IllegalStateException::new);
    return MemberDto.builder()
            .id(member.getId())
            .email(member.getEmail())
            .firstName(member.getFirstName())
            .lastName(member.getLastName())
            .build();
  }

}

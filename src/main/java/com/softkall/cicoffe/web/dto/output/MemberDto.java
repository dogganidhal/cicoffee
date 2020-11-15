package com.softkall.cicoffe.web.dto.output;

import com.softkall.cicoffe.model.entity.Member;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:03 PM
 * SoftKall™ All rights reserved.
 */



@Data
@Builder
public class MemberDto {

  private UUID id;
  private String firstName;
  private String lastName;
  private String email;

  public static MemberDto from(Member member) {
    return MemberDto.builder()
            .id(member.getId())
            .email(member.getEmail())
            .firstName(member.getFirstName())
            .lastName(member.getLastName())
            .build();
  }

}

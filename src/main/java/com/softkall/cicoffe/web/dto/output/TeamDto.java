package com.softkall.cicoffe.web.dto.output;

import com.softkall.cicoffe.model.entity.Team;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 9:58 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
public class TeamDto {

  private UUID id;
  private String name;
  private Collection<MemberDto> members;

  public static TeamDto from(Team team) {
    return from(team, true);
  }

  public static TeamDto from(Team team, boolean joinRelationships) {
    return TeamDto.builder()
            .id(team.getId())
            .name(team.getName())
            .members(joinRelationships ?
                    team.getMembers().stream()
                            .map(member -> MemberDto.builder()
                                    .id(member.getId())
                                    .email(member.getEmail())
                                    .firstName(member.getFirstName())
                                    .lastName(member.getLastName())
                                    .build()
                            )
                            .collect(Collectors.toList()) :
                    null
            )
            .build();
  }

}

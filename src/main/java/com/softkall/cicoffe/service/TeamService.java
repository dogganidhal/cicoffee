package com.softkall.cicoffe.service;

import com.softkall.cicoffe.web.dto.input.CreateInviteDto;
import com.softkall.cicoffe.web.dto.input.CreateTeamDto;
import com.softkall.cicoffe.web.dto.output.TeamDto;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:16 PM
 * SoftKall™ All rights reserved.
 */


public interface TeamService {
  TeamDto createTeam(CreateTeamDto team, UUID authorId);
  TeamDto addMember(UUID authorId, UUID memberId, UUID teamId);
  TeamDto joinTeam(UUID memberId, UUID teamId);
  TeamDto leaveTeam(UUID memberId, UUID teamId);
  void deleteTeam(UUID memberId, UUID teamId);
  Collection<TeamDto> myTeams(UUID memberId);
  void inviteByEmail(UUID memberId, UUID teamId, CreateInviteDto invite);
}

package com.softkall.cicoffe.service.impl;

import com.softkall.cicoffe.exception.ForbiddenException;
import com.softkall.cicoffe.model.entity.Member;
import com.softkall.cicoffe.model.entity.Team;
import com.softkall.cicoffe.model.repository.MemberRepository;
import com.softkall.cicoffe.model.repository.TeamRepository;
import com.softkall.cicoffe.service.MailService;
import com.softkall.cicoffe.service.TeamService;
import com.softkall.cicoffe.web.dto.input.CreateTeamDto;
import com.softkall.cicoffe.web.dto.output.TeamDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:18 PM
 * SoftKall™ All rights reserved.
 */


@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

  private final TeamRepository teamRepository;
  private final MemberRepository memberRepository;
  private final MailService mailService;

  @Override
  public TeamDto createTeam(CreateTeamDto request, UUID authorId) {
    Member author = memberRepository
            .findById(authorId)
            .orElseThrow(IllegalStateException::new);
    Team team = teamRepository.save(Team.builder()
            .name(request.getName())
            .creator(author)
            .members(Collections.singletonList(author))
            .build()
    );
    return TeamDto.from(team);
  }

  @Override
  public TeamDto addMember(UUID authorId, UUID memberId, UUID teamId) {
    Team team = teamRepository.getById(teamId);
    boolean userInTeam = team.getMembers().stream()
            .anyMatch(member -> member.getId().equals(authorId));
    if (!userInTeam) {
      throw new ForbiddenException();
    }
    Member member = memberRepository
            .findById(memberId)
            .orElseThrow(IllegalAccessError::new);
    team.getMembers().add(member);
    return TeamDto.from(teamRepository.save(team));
  }

  @Override
  public TeamDto joinTeam(UUID memberId, UUID teamId) {
    Team team = teamRepository.getById(teamId);
    boolean memberAlreadyInTeam = team.getMembers().stream()
            .anyMatch(m -> m.getId().equals(memberId));
    if (!memberAlreadyInTeam) {
      Member member = memberRepository.getById(memberId);
      team.getMembers().add(member);
      team = teamRepository.save(team);
    }
    return TeamDto.from(team);
  }

  @Override
  public TeamDto leaveTeam(UUID memberId, UUID teamId) {
    Team team = teamRepository.getById(teamId);
    team.getMembers()
            .removeIf(m -> m.getId().equals(memberId));
    return TeamDto.from(teamRepository.save(team));
  }

  @Override
  public void deleteTeam(UUID memberId, UUID teamId) {
    Team team = teamRepository.getById(teamId);
    if (!team.getCreator().getId().equals(memberId)) {
       throw new ForbiddenException();
    }
    teamRepository.deleteById(teamId);
  }

  @Override
  public Collection<TeamDto> myTeams(UUID memberId) {
    return teamRepository
            .findAllByMembers_Id(memberId)
            .stream()
            .map(TeamDto::from)
            .collect(Collectors.toList());
  }

  @Override
  public void inviteByEmail(UUID memberId, UUID teamId, Collection<String> emails) {
    Team team = teamRepository.getById(teamId);
    Member member = memberRepository.getById(memberId);
    emails.forEach(email -> mailService.sendTeamInvitation(team, email, member));
  }

}

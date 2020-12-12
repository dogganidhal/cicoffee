package com.softkall.cicoffe.web.controller;

import com.softkall.cicoffe.security.Authenticated;
import com.softkall.cicoffe.service.TeamService;
import com.softkall.cicoffe.web.dto.input.CreateTeamDto;
import com.softkall.cicoffe.web.dto.output.TeamDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;


/**
 *  @author Nidhal Dogga
 *  @created 11/13/2020 7:08 PM
 *  SoftKall™ All rights reserved.
 */


@RestController
@AllArgsConstructor
@RequestMapping("/api/team")
public class TeamController extends AbstractController {

    private final TeamService teamService;

    @PostMapping
    @Authenticated
    public Mono<TeamDto> createTeam(@Valid @RequestBody CreateTeamDto team, Authentication authentication) {
        return Mono.just(teamService.createTeam(team, getMemberId(authentication)));
    }

    @Authenticated
    @PostMapping("/{teamId}/members/{memberId}")
    public Mono<TeamDto> addMember(@PathVariable UUID teamId, @PathVariable UUID memberId, Authentication authentication) {
        return Mono.just(teamService.addMember(getMemberId(authentication), memberId, teamId));
    }

    @Authenticated
    @PostMapping("/{teamId}/join")
    public Mono<TeamDto> joinTeam(@PathVariable UUID teamId, Authentication authentication) {
        return Mono.just(teamService.joinTeam(getMemberId(authentication), teamId));
    }

    @Authenticated
    @PostMapping("/{teamId}/leave")
    public Mono<TeamDto> leaveTeam(@PathVariable UUID teamId, Authentication authentication) {
        return Mono.just(teamService.leaveTeam(getMemberId(authentication), teamId));
    }

    @Authenticated
    @DeleteMapping("/{teamId}")
    public Mono<TeamDto> deleteTeam(@PathVariable UUID teamId, Authentication authentication) {
        return Mono.fromRunnable(() -> teamService.deleteTeam(getMemberId(authentication), teamId));
    }

    @Authenticated
    @GetMapping
    public Mono<Collection<TeamDto>> myTeams(Authentication authentication) {
        return Mono.just(teamService.myTeams(getMemberId(authentication)));
    }

    @Authenticated
    @PostMapping("/{teamId}/invite")
    public Mono<Void> inviteByEmail(@PathVariable UUID teamId, @RequestBody Collection<String> emails, Authentication authentication) {
        return Mono.fromRunnable(() -> teamService.inviteByEmail(getMemberId(authentication), teamId, emails));
    }

}

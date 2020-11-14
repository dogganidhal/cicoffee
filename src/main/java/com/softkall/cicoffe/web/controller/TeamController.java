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
import java.util.UUID;


/**
 *  @author Nidhal Dogga
 *  @since 11/13/2020 7:08 PM
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
    public void addMember(@PathVariable UUID teamId, @PathVariable UUID memberId, Authentication authentication) {
        teamService.addMember(getMemberId(authentication), memberId, teamId);
    }

}

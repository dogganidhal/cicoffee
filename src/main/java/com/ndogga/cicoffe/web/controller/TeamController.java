package com.ndogga.cicoffe.web.controller;

import com.ndogga.cicoffe.service.TeamService;
import com.ndogga.cicoffe.web.dto.input.CreateTeamDto;
import com.ndogga.cicoffe.web.dto.output.TeamDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


/**
 *  @author Nidhal Dogga
 *  @since 11/13/2020 7:08 PM
 *  Prize & Fun™ All rights reserved.
 */


@RestController
@AllArgsConstructor
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public TeamDto createTeam(@Valid @RequestBody CreateTeamDto team) {
        // TODO: Grab the user id from auth
        return teamService.createTeam(team, UUID.randomUUID());
    }

    @PostMapping("/{teamId}/members/{memberId}")
    public void addMember(@PathVariable UUID teamId, @PathVariable UUID memberId) {
        // TODO: Grab the user id from auth
        teamService.addMember(UUID.randomUUID(), memberId, teamId);
    }

}

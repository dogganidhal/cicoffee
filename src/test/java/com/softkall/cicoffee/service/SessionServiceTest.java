package com.softkall.cicoffee.service;


import com.softkall.cicoffe.CICoffeeApplication;
import com.softkall.cicoffe.model.entity.Member;
import com.softkall.cicoffe.model.entity.Team;
import com.softkall.cicoffe.model.repository.MemberRepository;
import com.softkall.cicoffe.model.repository.TeamRepository;
import com.softkall.cicoffe.service.MemberService;
import com.softkall.cicoffe.service.SessionService;
import com.softkall.cicoffe.web.dto.input.CreateMemberDto;
import com.softkall.cicoffe.web.dto.input.CreateSessionDto;
import com.softkall.cicoffe.web.dto.output.SessionDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.UUID;

/**
 * @author nidhaldogga
 * @created 31/12/2020 01:37
 * SoftKall™ All rights reserved.
 */



@ActiveProfiles("test")
@SpringBootTest(classes = CICoffeeApplication.class)
public class SessionServiceTest {

  @Autowired
  private SessionService sessionService;

  private static UUID memberId;
  private static UUID teamId;

  @BeforeAll
  public static void initDatabase(
          @Autowired MemberRepository memberRepository,
          @Autowired TeamRepository teamRepository
  ) {
    Member member = memberRepository.save(Member.builder()
            .email(UUID.randomUUID().toString())
            .firstName(UUID.randomUUID().toString())
            .lastName(UUID.randomUUID().toString())
            .passwordHash(UUID.randomUUID().toString())
            .build()
    );
    memberId = member.getId();

    teamId = teamRepository.save(Team.builder()
            .creator(member)
            .name(UUID.randomUUID().toString())
            .members(Collections.emptyList())
            .build()
    ).getId();
  }

  @BeforeEach
  public void initTimezone() {
    System.setProperty("user.timezone", "UTC");
  }

  @Test
  public void shouldSaveUTCDatesFromUTCLocalTimeZone() {
    LocalDateTime startLocalDateTime = LocalDateTime.now();
    LocalDateTime endLocalDateTime = LocalDateTime.now().plusMinutes(10);
    Instant startInstant = startLocalDateTime.toInstant(ZoneOffset.UTC);
    Instant endInstant = endLocalDateTime.toInstant(ZoneOffset.UTC);

    SessionDto session = sessionService.createSession(CreateSessionDto.builder()
            .startDate(startLocalDateTime)
            .endDate(endLocalDateTime)
            .teamId(teamId)
            .build(),
            memberId
    );

    Assertions.assertEquals(
            startInstant.getEpochSecond(),
            session.getStartDate().toInstant(ZoneOffset.UTC).getEpochSecond()
    );

    Assertions.assertEquals(
            endInstant.getEpochSecond(),
            session.getEndDate().toInstant(ZoneOffset.UTC).getEpochSecond()
    );
  }

  @AfterAll
  public static void flushDatabase(
          @Autowired MemberRepository memberRepository,
          @Autowired TeamRepository teamRepository
  ) {
//    memberRepository.deleteById(memberId);
//    teamRepository.deleteById(teamId);
  }

}

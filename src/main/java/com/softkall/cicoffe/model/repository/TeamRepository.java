package com.softkall.cicoffe.model.repository;

import com.softkall.cicoffe.model.entity.Member;
import com.softkall.cicoffe.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @since 11/13/2020 7:07 PM
 * SoftKall™ All rights reserved.
 */


public interface TeamRepository extends JpaRepository<Team, UUID> {
  Collection<Team> findAllByMembers_Id(UUID memberId);
}

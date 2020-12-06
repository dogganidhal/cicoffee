package com.softkall.cicoffe.model.repository;

import com.softkall.cicoffe.model.entity.Member;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/13/2020 7:06 PM
 * SoftKall™ All rights reserved.
 */


public interface MemberRepository extends AbstractRepository<Member, UUID> {
  Optional<Member> findByEmail(String email);
  Long countByEmail(String email);
  @Query("SELECT m FROM members m WHERE LOWER(m.email) LIKE %?1% OR LOWER(m.firstName) LIKE %?1% OR LOWER(m.lastName) LIKE %?1%")
  Collection<Member> searchMembers(String query);
}

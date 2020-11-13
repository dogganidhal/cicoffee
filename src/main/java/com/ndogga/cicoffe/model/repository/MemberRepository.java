package com.ndogga.cicoffe.model.repository;

import com.ndogga.cicoffe.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @since 11/13/2020 7:06 PM
 * Prize & Fun™ All rights reserved.
 */


public interface MemberRepository extends JpaRepository<Member, UUID> {
  Optional<Member> findByEmail(String email);
}

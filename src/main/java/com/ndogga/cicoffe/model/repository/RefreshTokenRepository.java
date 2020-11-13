package com.ndogga.cicoffe.model.repository;

import com.ndogga.cicoffe.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 11:10 PM
 * Prize & Fun™ All rights reserved.
 */


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
  Collection<RefreshToken> findAllByMemberId(UUID memberId);
}

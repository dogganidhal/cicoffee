package com.softkall.cicoffe.model.repository;

import com.softkall.cicoffe.model.entity.RefreshToken;

import java.util.Collection;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 11:10 PM
 * SoftKall™ All rights reserved.
 */


public interface RefreshTokenRepository extends AbstractRepository<RefreshToken, String> {
  Collection<RefreshToken> findAllByMemberId(UUID memberId);
}

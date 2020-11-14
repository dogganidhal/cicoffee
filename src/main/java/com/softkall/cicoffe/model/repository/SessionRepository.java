package com.softkall.cicoffe.model.repository;

import com.softkall.cicoffe.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:35 PM
 * SoftKall™ All rights reserved.
 */


public interface SessionRepository extends JpaRepository<Session, UUID> {

}

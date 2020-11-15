package com.softkall.cicoffe.model.repository;


import com.softkall.cicoffe.exception.NotFoundException;
import com.softkall.cicoffe.model.entity.MobileDevice;

import java.util.Optional;
import java.util.UUID;


/**
 * @author nidhaldogga
 * @created 15/11/2020 16:43
 * SoftKall™ All rights reserved.
 */


public interface MobileDeviceRepository extends AbstractRepository<MobileDevice, UUID> {
  Optional<MobileDevice> findByIdentifier(String identifier);
  boolean existsByIdentifier(String identifier);

  default MobileDevice getByIdentifier(String identifier) {
    return findByIdentifier(identifier)
            .orElseThrow(NotFoundException::new);
  }
}

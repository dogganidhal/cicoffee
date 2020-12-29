package com.softkall.cicoffe.model.repository;
import com.softkall.cicoffe.model.entity.ResetCode;

import java.util.Optional;

public interface ResetCodeRepository extends AbstractRepository<ResetCode, String> {
    Optional<ResetCode> findByCode(String code);
}

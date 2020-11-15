package com.softkall.cicoffe.model.repository;


import com.softkall.cicoffe.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * @author Nidhal Dogga
 * @created 11/15/2020 12:22 AM
 * SoftKall™ All rights reserved.
 */


@NoRepositoryBean
public interface AbstractRepository<T, R> extends JpaRepository<T, R> {
  default T getById(R id) {
    return findById(id)
            .orElseThrow(NotFoundException::new);
  }
}

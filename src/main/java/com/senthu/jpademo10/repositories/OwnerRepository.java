package com.senthu.jpademo10.repositories;

import com.senthu.jpademo10.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
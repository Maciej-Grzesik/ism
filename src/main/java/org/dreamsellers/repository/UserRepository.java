package org.dreamsellers.repository;

import org.dreamsellers.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, UserEntity> {
  UserEntity getUserById(long id);
}

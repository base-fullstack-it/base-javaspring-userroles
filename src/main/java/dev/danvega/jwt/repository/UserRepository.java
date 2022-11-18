package dev.danvega.jwt.repository;
import dev.danvega.jwt.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Integer> {

    Optional<UserEntity> findByUsername(String username);

}

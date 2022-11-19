package dev.danvega.jwt.repository;
import dev.danvega.jwt.entity.UserEntity;
import dev.danvega.jwt.entity.security.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> findByUsername(String username);

}

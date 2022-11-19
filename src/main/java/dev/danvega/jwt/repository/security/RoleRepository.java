package dev.danvega.jwt.repository.security;

import dev.danvega.jwt.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String customer);
}

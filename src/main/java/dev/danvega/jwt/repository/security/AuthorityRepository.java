package dev.danvega.jwt.repository.security;

import dev.danvega.jwt.entity.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}

package dev.danvega.jwt.domain.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jt on 6/21/20.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String permission;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;
}

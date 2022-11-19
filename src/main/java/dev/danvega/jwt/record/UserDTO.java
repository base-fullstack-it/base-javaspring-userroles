package dev.danvega.jwt.record;

import dev.danvega.jwt.entity.UserEntity;
import dev.danvega.jwt.entity.security.User;
import lombok.Builder;

@Builder
public record UserDTO(Integer id, String username, String password) {

    public static UserDTO from(User user){
        return UserDTO.builder()
                .id(user.getId())
                .build();
    }

}

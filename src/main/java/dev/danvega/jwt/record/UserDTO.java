package dev.danvega.jwt.record;

import dev.danvega.jwt.entity.UserEntity;
import lombok.Builder;

@Builder
public record UserDTO(Integer id, String username, String password) {

    public static UserDTO from(UserEntity userEntity){
        return UserDTO.builder()
                .id(userEntity.getId())
                .build();
    }

}

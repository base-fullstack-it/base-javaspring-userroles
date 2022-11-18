package dev.danvega.jwt.record;

import dev.danvega.jwt.entity.UserEntity;
import lombok.Builder;

@Builder
public record User(Integer id, String username, String password) {

    public static User from(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .build();
    }

}

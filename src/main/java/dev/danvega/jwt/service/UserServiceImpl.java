package dev.danvega.jwt.service;


import dev.danvega.jwt.entity.UserEntity;
import dev.danvega.jwt.exception.GeneralMessageException;
import dev.danvega.jwt.record.User;
import dev.danvega.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mapping.Alias.ofNullable;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @SneakyThrows
    @Override
    public User createUser(User user) {
        String username = user.username().trim();
        Optional<UserEntity> byUsername = userRepository.findByUsername(username);
        if(Objects.nonNull(byUsername) && !byUsername.isEmpty()) throw new GeneralMessageException("username already exist");
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
//        if (ofNullable(userRepository.findByUsername(username)).isPresent()) {
//            throw new GeneralMessageException("user by username already exists "+ username );
//        }
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(passwordEncoder.encode(user.password()));
            userEntity.setRoles("read");
            UserEntity userEntityAfterSave = userRepository.save(userEntity);

//            UserRoleEntity userRoleEntity = new UserRoleEntity();
//            userRoleEntity.setUserEmail(username);
//            userRoleEntity.setRole(UserRoles.USER_ROLE);
//            userRoleRepository.save(userRoleEntity);
     return User.from(userEntityAfterSave);
    }



}

package dev.danvega.jwt.service;


import dev.danvega.jwt.entity.security.User;
import dev.danvega.jwt.exception.GeneralMessageException;
import dev.danvega.jwt.record.UserDTO;
import dev.danvega.jwt.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @SneakyThrows
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        String username = userDTO.username().trim();
        Optional<User> byUsername = userRepository.findByUsername(username);
        if(Objects.nonNull(byUsername) && !byUsername.isEmpty()) throw new GeneralMessageException("username already exist");
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
//        if (ofNullable(userRepository.findByUsername(username)).isPresent()) {
//            throw new GeneralMessageException("userDTO by username already exists "+ username );
//        }
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(userDTO.password()));
//            user.setRoles("read");
            User userAfterSave = userRepository.save(user);

//            UserRoleEntity userRoleEntity = new UserRoleEntity();
//            userRoleEntity.setUserEmail(username);
//            userRoleEntity.setRole(UserRoles.USER_ROLE);
//            userRoleRepository.save(userRoleEntity);
     return UserDTO.from(userAfterSave);
    }



}

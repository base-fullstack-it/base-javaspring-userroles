package dev.danvega.jwt.service;

import dev.danvega.jwt.record.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO user);
}

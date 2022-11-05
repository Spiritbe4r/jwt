package com.cardenas.rest.service;

import com.cardenas.rest.dtos.RegisterDto;
import com.cardenas.rest.dtos.RegistrationRequest;
import com.cardenas.rest.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
     void register(RegisterDto request);

     List<User> getUsers();

     User getUserById(Long id);

     User updateUser(User user);

     void deleteUser(Long id);

     Optional<User> getByUsername(String username);

     boolean existByUsername(String username);

     boolean existByEmail(String email);


}

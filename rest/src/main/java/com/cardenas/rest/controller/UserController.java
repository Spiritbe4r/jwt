package com.cardenas.rest.controller;

import com.cardenas.rest.dtos.RegisterDto;
import com.cardenas.rest.dtos.RegistrationRequest;
import com.cardenas.rest.entity.User;
import com.cardenas.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;




    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@RequestParam Long id) {
         User user =userService.getUserById(id);
        return new ResponseEntity<>(user ,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>>getUsers () {
        List<User> users=userService.getUsers();
        return new ResponseEntity<>(users ,HttpStatus.OK);

    }

    @PutMapping()
    public ResponseEntity<User> update (@RequestBody User request){
         User user =userService.updateUser(request);

        return new ResponseEntity<>(user ,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete (@RequestParam Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

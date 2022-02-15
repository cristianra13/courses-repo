/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MercadoLibre.Service.User;

import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alejandro Greggio
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Create new user")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addNewUser(@RequestBody User user) {

        User n = new User();
        n.setUsername(user.getUsername());
        n.setPassword(user.getPassword());

        if (!userRepository.findByUsername(user.getUsername()).isPresent()) {
            userRepository.save(n);
        } else {
            return new ResponseEntity<>("User Duplicate ", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User Created", HttpStatus.OK);
    }
    
    @ApiOperation(value = "Return All User")
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "Return User for Id")
    @RequestMapping(method = RequestMethod.GET, value = "id/{id}")
    Optional<User> findById(@PathVariable(required = false, name = "id") int id) {
        return userRepository.findById(id);
    }
    
    @ApiOperation(value = "Return User for Username")
    @RequestMapping(method = RequestMethod.GET, value = "username/{username}")
    Optional<User> findById(@PathVariable(required = false, name = "username") String username) {
        return userRepository.findByUsername(username);
    }

}

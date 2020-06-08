package com.tarasPlus.serverRest.controller;

import com.tarasPlus.serverRest.model.Role;
import com.tarasPlus.serverRest.model.User;
import com.tarasPlus.serverRest.service.RoleService;
import com.tarasPlus.serverRest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private UserServiceImpl userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserServiceImpl userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/users/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> findAll(){
        return roleService.findAll();
    }

    @GetMapping(value = "/users/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Role>> findById(@PathVariable(value = "id") Long id) {
        Optional<Role> role = roleService.findById(id);
        return ResponseEntity.ok().body(role);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/users/name/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByName(@PathVariable String username) {
        User user = userService.findByLogin(username);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/users",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody @Valid User user){
        HttpHeaders headers =  new HttpHeaders();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.addUser(user);
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(
                                           @RequestBody @Valid User user){
        HttpHeaders headers = new HttpHeaders();

        if (user == null) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userService.updateUser(user)) {
            return new ResponseEntity<>(user, headers, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

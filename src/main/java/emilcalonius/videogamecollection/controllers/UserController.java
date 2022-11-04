package emilcalonius.videogamecollection.controllers;

import emilcalonius.videogamecollection.mappers.UserMapper;
import emilcalonius.videogamecollection.models.User;
import emilcalonius.videogamecollection.models.UserGETDTO;
import emilcalonius.videogamecollection.models.UserPOSTDTO;
import emilcalonius.videogamecollection.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<User> getCurrentUser() {
        // TODO
        return ResponseEntity.ok(userService.findById(1));
    }

    // TODO user search
}

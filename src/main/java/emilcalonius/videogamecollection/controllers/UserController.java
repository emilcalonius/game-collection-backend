package emilcalonius.videogamecollection.controllers;

import emilcalonius.videogamecollection.mappers.UserMapper;
import emilcalonius.videogamecollection.models.User;
import emilcalonius.videogamecollection.models.UserGETDTO;
import emilcalonius.videogamecollection.models.UserPOSTDTO;
import emilcalonius.videogamecollection.security.JWTUtil;
import emilcalonius.videogamecollection.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    private JWTUtil jwtUtil;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    // TODO user search

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping()
    public ResponseEntity getLoggedInUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        String name = jwtUtil.validateTokenAndRetrieveSubject(authorization.split(" ")[1]);
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        URI location = URI.create(baseUrl+"/api/user/"+name);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        return new ResponseEntity<>("", responseHeaders, HttpStatus.SEE_OTHER);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{username}")
    public ResponseEntity getUser(@PathVariable String username) {
        return ResponseEntity.ok(userMapper.userToUserGETDTO(userService.findByName(username)));
    }

    // TODO update user
}

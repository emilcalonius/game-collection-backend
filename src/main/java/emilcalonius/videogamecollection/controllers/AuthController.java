package emilcalonius.videogamecollection.controllers;

import emilcalonius.videogamecollection.mappers.UserMapper;
import emilcalonius.videogamecollection.models.LoginCredentials;
import emilcalonius.videogamecollection.models.User;
import emilcalonius.videogamecollection.models.UserPOSTDTO;
import emilcalonius.videogamecollection.security.JWTUtil;
import emilcalonius.videogamecollection.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "User successfully added",
            content = @Content),
        @ApiResponse(responseCode = "409",
            description = "User already exists with that username",
            content = @Content)

    })
    @CrossOrigin(origins = "http://calonius.me")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserPOSTDTO userPOSTDTO) {
        // If username exists don't add
        if(userService.findByName(userPOSTDTO.getName()) != null) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("User already exists with that username");
        }
        String encodedPass = passwordEncoder.encode(userPOSTDTO.getPassword());
        userPOSTDTO.setPassword(encodedPass);
        User user = userService.add(userMapper.userPOSTDTOToUser(userPOSTDTO));
        String token = jwtUtil.generateToken(user.getName(), user.getId());
        URI location = URI.create("user/" + user.getId());
        return ResponseEntity
            .created(location)
            .body(Collections.singletonMap("jwt-token", token));
    }

    @CrossOrigin(origins = "http://calonius.me")
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginCredentials body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(body.getName(), body.getPassword());

            authManager.authenticate(authInputToken);

            User user = userService.findByName(body.getName());

            String token = jwtUtil.generateToken(user.getName(), user.getId());

            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException authExc) {
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
}

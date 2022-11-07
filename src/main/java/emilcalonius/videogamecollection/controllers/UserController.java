package emilcalonius.videogamecollection.controllers;

import emilcalonius.videogamecollection.mappers.UserMapper;
import emilcalonius.videogamecollection.models.User;
import emilcalonius.videogamecollection.models.UserGETDTO;
import emilcalonius.videogamecollection.models.UserPOSTDTO;
import emilcalonius.videogamecollection.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    // TODO get current user

    // TODO user search

    // TODO update user

    @Operation(summary = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "User successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "409",
                    description = "User already exists with that username",
                    content = @Content)

    })
}

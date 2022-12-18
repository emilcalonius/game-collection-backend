package emilcalonius.videogamecollection.controllers;

import emilcalonius.videogamecollection.exceptions.EntityNotFoundException;
import emilcalonius.videogamecollection.mappers.GameMapper;
import emilcalonius.videogamecollection.models.Game;
import emilcalonius.videogamecollection.models.GameDTO;
import emilcalonius.videogamecollection.models.User;
import emilcalonius.videogamecollection.security.JWTUtil;
import emilcalonius.videogamecollection.services.GameService;
import emilcalonius.videogamecollection.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/game")
public class GameController {
    private final GameService gameService;
    private final GameMapper gameMapper;
    private final UserService userService;
    @Autowired
    private JWTUtil jwtUtil;

    public GameController(GameService gameService, GameMapper gameMapper, UserService userService) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Collection<Game>> getAllGamesForUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        String name = jwtUtil.validateTokenAndRetrieveSubject(authorization.split(" ")[1]);
        User user = userService.findByName(name);
        return ResponseEntity.ok(gameService.findAllByUser(user.getId()));
    }

    @GetMapping("/{game_id}")
    public ResponseEntity<Game> getGameById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @PathVariable int game_id) {
        String name = jwtUtil.validateTokenAndRetrieveSubject(authorization.split(" ")[1]);
        User user = userService.findByName(name);
        if(!gameService.ownsGame(user.getId(), game_id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameService.findGameById(user.getId(), game_id));
    }

    @PostMapping
    public ResponseEntity addGame(@RequestBody GameDTO gameDTO) {
        if(gameService.ownsGame(gameDTO.getUser_id(), gameDTO.getGame_id()))
            return new ResponseEntity("User already has game with id: "+gameDTO.getGame_id(), HttpStatus.CONFLICT);
        Game game = gameService.add(gameMapper.gameDTOToGame(gameDTO));
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        URI location = URI.create(baseUrl+"/api/game"+game.getGame_id());

        return ResponseEntity
                .created(location)
                .header("Access-Control-Allow-Headers",
                    "Authorization, Access-Control-Allow-Headers, Origin, Accept, location, " +
                    "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
                .body(game.getGame_id());

    }

    @PatchMapping
    public ResponseEntity updateGame(@RequestBody GameDTO gameDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        String name = jwtUtil.validateTokenAndRetrieveSubject(authorization.split(" ")[1]);
        User user = userService.findByName(name);
        if(user.getId() != gameDTO.getUser_id()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Can not edit other peoples collection");
        }
        gameService.update(gameMapper.gameDTOToGame(gameDTO));
        return ResponseEntity.noContent().build();
    }
}

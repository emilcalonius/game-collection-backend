package emilcalonius.videogamecollection.controllers;

import emilcalonius.videogamecollection.mappers.GameMapper;
import emilcalonius.videogamecollection.models.Game;
import emilcalonius.videogamecollection.models.GameDTO;
import emilcalonius.videogamecollection.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/game")
public class GameController {
    private final GameService gameService;

    private final GameMapper gameMapper;

    public GameController(GameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @GetMapping
    public ResponseEntity<Collection<Game>> getAllGamesForUser() {
        return ResponseEntity.ok(gameService.findAllByUser(1));
    }

    @PostMapping
    public ResponseEntity addGame(@RequestBody GameDTO gameDTO) {
        Game game = gameService.add(gameMapper.gameDTOToGame(gameDTO));
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        URI location = URI.create(baseUrl+"/api/v1/post/"+game.getId());

        return ResponseEntity
                .created(location)
                .header("Access-Control-Allow-Headers",
                    "Authorization, Access-Control-Allow-Headers, Origin, Accept, location, " +
                    "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
                .body(game.getId());

    }
}

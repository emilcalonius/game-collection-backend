package emilcalonius.videogamecollection.controllers;

import emilcalonius.videogamecollection.models.Game;
import emilcalonius.videogamecollection.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    ResponseEntity<Collection<Game>> getAllGamesForUser() {
        return ResponseEntity.ok(gameService.findAllByUser(1));
    }
}

package emilcalonius.videogamecollection.services;

import emilcalonius.videogamecollection.models.Game;
import emilcalonius.videogamecollection.repositories.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Set<Game> findAllByUser(int user_id) {
        return gameRepository.findAllByUser(user_id);
    }

    @Override
    public boolean ownsGame(int user_id, int game_id) {
        return gameRepository.ownsGame(user_id, game_id);
    }

    @Override
    public Game findGameById(int user_id, int game_id) {
        return gameRepository.findGameById(user_id, game_id);
    }

    @Override
    public Game findById(Integer id) {
        return gameRepository.findById(id).get();
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game add(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game update(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void deleteById(Integer id) {
        if(!gameRepository.existsById(id)) {
            logger.warn("No user exists with ID: " + id);
            return;
        }
        gameRepository.deleteById(id);
    }

    @Override
    public void delete(Game game) {
        gameRepository.delete(game);
    }
}

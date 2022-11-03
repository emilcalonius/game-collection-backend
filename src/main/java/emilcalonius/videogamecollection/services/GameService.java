package emilcalonius.videogamecollection.services;

import emilcalonius.videogamecollection.models.Game;

import java.util.Set;

public interface GameService extends CrudService<Game, Integer> {
    Set<Game> findAllByUser(int user_id);
}

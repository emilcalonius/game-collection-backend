package emilcalonius.videogamecollection.repositories;

import emilcalonius.videogamecollection.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query("SELECT g FROM Game g WHERE g.user.id = :user_id")
    Set<Game> findAllByUser(int user_id);

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END FROM Game g WHERE g.game_id = :game_id AND g.user.id = :user_id")
    boolean ownsGame(int user_id, int game_id);

    @Query("SELECT g FROM Game g WHERE g.user.id = :user_id AND g.game_id = :game_id")
    Game findGameById(int user_id, int game_id);
}

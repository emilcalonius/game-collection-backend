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
}

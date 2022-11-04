package emilcalonius.videogamecollection.repositories;

import emilcalonius.videogamecollection.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.name LIKE %?1%")
    Set<User> findAllByName(String name);

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    User findByName(String name);
}

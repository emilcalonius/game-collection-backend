package emilcalonius.videogamecollection.services;

import emilcalonius.videogamecollection.models.User;
import emilcalonius.videogamecollection.repositories.GameRepository;
import emilcalonius.videogamecollection.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Set<User> findAllByName(String name) {
        return userRepository.findAllByName(name);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if(!userRepository.existsById(id)) {
            logger.warn("No user exists with ID: " + id);
            return;
        }
        // Delete all user's games when deleting user
        gameRepository.findAllByUser(id).forEach(gameRepository::delete);
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(User user) {
        if(!userRepository.existsById(user.getId())) {
            logger.warn("No user exists with ID: " + user.getId());
            return;
        }
        // Delete all user's games when deleting user
        gameRepository.findAllByUser(user.getId()).forEach(gameRepository::delete);
        userRepository.delete(user);
    }
}

package emilcalonius.videogamecollection.services;

import emilcalonius.videogamecollection.models.User;

import java.util.Set;

public interface UserService extends CrudService<User, Integer> {
    Set<User> findAllByName(String name);
    User findByName(String name);
}

package emilcalonius.videogamecollection.mappers;

import emilcalonius.videogamecollection.models.Game;
import emilcalonius.videogamecollection.models.GameDTO;
import emilcalonius.videogamecollection.models.User;
import emilcalonius.videogamecollection.repositories.UserRepository;
import emilcalonius.videogamecollection.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class GameMapper {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Mapping(target = "user", source = "user_id", qualifiedByName = "idToUser")
    public abstract Game gameDTOToGame(GameDTO gameDTO);
    @Mapping(target = "user_id", source = "user.id")
    public abstract GameDTO gameToGameDTO(Game game);

    @Named("idToUser")
    User idToUser(Integer id) {
        if(!userRepository.existsById(id)) {
            return null;
        }
        return userService.findById(id);
    }
}

package emilcalonius.videogamecollection.mappers;

import emilcalonius.videogamecollection.models.User;
import emilcalonius.videogamecollection.models.UserGETDTO;
import emilcalonius.videogamecollection.models.UserPOSTDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    User userGETDTOToUser(UserGETDTO userGETDTO);
    UserGETDTO userToUserGETDTO(User user);
    User userPOSTDTOToUser(UserPOSTDTO userPOSTGETDTO);
    UserPOSTDTO userToUserPOSTDTO(User user);
}

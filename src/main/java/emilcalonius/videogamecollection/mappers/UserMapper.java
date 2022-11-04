package emilcalonius.videogamecollection.mappers;

import emilcalonius.videogamecollection.models.User;
import emilcalonius.videogamecollection.models.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    User userDTOToUser(UserDTO userDTO);
    UserDTO userToUserDTO(User user);
}

package emilcalonius.videogamecollection.models;

import lombok.Data;

@Data
public class UserPOSTDTO {
    private int id;
    private String name;
    private String bio;
    private String avatar;
    private String password;
}
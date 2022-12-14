package emilcalonius.videogamecollection.models;

import lombok.Data;

@Data
public class GameDTO {
    private int id;
    private int game_id;
    private int user_id;
    private String status;
    private int rating;
    private boolean completed;
    private String header_image;
    private String name;
}

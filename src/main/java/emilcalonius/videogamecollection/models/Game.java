package emilcalonius.videogamecollection.models;

import emilcalonius.videogamecollection.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int game_id;
    @ManyToOne
    @JoinColumn(name = "`user`", nullable = false)
    private User user;
    @Column(nullable = false)
    private Status status;
    @Column
    private int rating;
}

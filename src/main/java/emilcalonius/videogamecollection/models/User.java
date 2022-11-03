package emilcalonius.videogamecollection.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 150, nullable = false)
    private String name;
    @Column(length = 300)
    private String avatar;
    @Column(length = 4000)
    private String bio;
    @Column(nullable = false)
    private String password;
}

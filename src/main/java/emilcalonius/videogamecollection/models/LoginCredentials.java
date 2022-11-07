package emilcalonius.videogamecollection.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Data
public class LoginCredentials {
    private String name;
    private String password;
}

package emilcalonius.videogamecollection.services;

import emilcalonius.videogamecollection.models.User;
import emilcalonius.videogamecollection.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
        if(user == null) throw new UsernameNotFoundException("Could not find User with name = " + name);
        return new org.springframework.security.core.userdetails.User(
            name,
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}

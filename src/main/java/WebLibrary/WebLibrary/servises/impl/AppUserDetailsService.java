package WebLibrary.WebLibrary.servises.impl;


import WebLibrary.WebLibrary.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class AppUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByFirstName(username)
                .map(u -> new User(
                        u.getEmail(),
                        u.getPassword(),
                        u.getRoles().stream()
                                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name()))
                                .collect(Collectors.toList())
                )).orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}

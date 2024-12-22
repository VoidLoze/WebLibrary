package WebLibrary.WebLibrary.servises.impl;

import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.dto.UserRegistrationDTO;
import WebLibrary.WebLibrary.enums.UserRoles;
import WebLibrary.WebLibrary.repository.UserRepository;
import WebLibrary.WebLibrary.repository.UserRoleRepository;
import WebLibrary.WebLibrary.servises.AuthService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;


    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void register(UserRegistrationDTO registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<Reader> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = userRoleRepository.
                findRoleByName(UserRoles.USER).orElseThrow();

        Reader user = new Reader(
                registrationDTO.getEmail(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getName(),
                registrationDTO.getSurname(),
                registrationDTO.getPhoneNumber(),
                registrationDTO.getAge());

        user.setRoles(List.of(userRole));

        this.userRepository.save(user);
    }

    @Override
    public Reader getUser(String username) {
        return userRepository.findByFirstName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
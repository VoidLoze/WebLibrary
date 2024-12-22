package WebLibrary.WebLibrary;


import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.domain.Role;
import WebLibrary.WebLibrary.enums.UserRoles;
import WebLibrary.WebLibrary.repository.UserRepository;
import WebLibrary.WebLibrary.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Init implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;

    public Init(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, @Value("${app.default.password}") String defaultPassword) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var adminRole = new Role(UserRoles.ADMIN);
            var normalUserRole = new Role(UserRoles.USER);
            userRoleRepository.save(adminRole);
            userRoleRepository.save(normalUserRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initNormalUser();
        }
    }

    private void initAdmin(){
        try {
            var adminRole = userRoleRepository.findRoleByName(UserRoles.ADMIN).orElseThrow();
            var adminUser = new Reader("admin@example.com", passwordEncoder.encode(defaultPassword),"Admin", "Admin", "89042962034", 30);
            adminUser.setRoles(List.of(adminRole));
            userRepository.save(adminUser);
        } catch (Exception e) {
            e.printStackTrace(); //Replace with proper logging for production
            //Consider alternative actions like logging, retrying, or alerting
        }
    }


    private void initNormalUser() {
        try {
            var userRole = userRoleRepository.findRoleByName(UserRoles.USER).orElseThrow();
            var normalUser = new Reader("user@example.com", passwordEncoder.encode(defaultPassword), "user", "user", "89042962034", 30);
            normalUser.setRoles(List.of(userRole));
            userRepository.save(normalUser);
        } catch (Exception e) {
            e.printStackTrace(); // Замените на подходящее логирование
        }
    }
}
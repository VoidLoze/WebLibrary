package WebLibrary.WebLibrary.servises;

import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.dto.UserRegistrationDTO;

public interface AuthService {
    void register(UserRegistrationDTO registrationDTO);
    Reader getUser(String username);
}

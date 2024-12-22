package WebLibrary.WebLibrary.controller;

import WebLibrary.WebLibrary.dto.ReaderDTO;
import WebLibrary.WebLibrary.dto.UserRegistrationDTO;
import WebLibrary.WebLibrary.servises.AuthService;
import WebLibrary.WebLibrary.servises.ReaderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class AuthController {

    private AuthService authService;
    private ReaderService readerService;

    @Autowired
    public AuthController(AuthService authService, ReaderService readerService) {
        this.authService = authService;
        this.readerService = readerService;
    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDTO initForm() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "reg/register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDTO userRegistrationDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);

            return "redirect:/users/register";
        }

        this.authService.register(userRegistrationDto);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "reg/login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }


//    @GetMapping("/profile")
//    public String profile(Principal principal, Model model) {
//        String username = principal.getName();
//        Reader user = authService.getUser(username);
//
//        UserView userProfileView = new UserView(
//                user.getEmail(),
//                username,
//                user.getLastName(),
//                user.getPhoneNumber(),
//                user.getAge()
//        );
//
//        model.addAttribute("user", userProfileView);
//
//        return "reg/profile";
//    }
    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();

        ReaderDTO userProfileDTO = readerService.getUserProfile(username);

        model.addAttribute("user", userProfileDTO);

        return "profile";
    }
}
package WebLibrary.WebLibrary.controller;

import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.dto.FriendDTO;
import WebLibrary.WebLibrary.servises.FriendService;
import WebLibrary.WebLibrary.servises.ReaderService;
import WebLibrary.WebLibrary.servises.ReviewService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webLibraryView.library.controllers.friend.FriendController;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.friend.FriendListDetailsViewModel;
import webLibraryView.library.viewmodel.friend.FriendViewModel;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/friend")
public class FriendControllerImpl implements FriendController {
    private final FriendService friendService;
    private final ReaderService readerService;
    private final ReviewService reviewService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public FriendControllerImpl(FriendService friendService, ReaderService readerService, ReviewService reviewService) {
        this.friendService = friendService;
        this.readerService = readerService;
        this.reviewService = reviewService;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }


//    @GetMapping("/add")
//    public String createForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        var viewModel = new FriendListCreateViewModel(createBaseViewModel("Добавить друга"));
//
//        String email = userDetails.getUsername();
//
//        Optional<Reader> currentUserOptional = readerService.findByEmail(email);
//
//        if (currentUserOptional.isPresent()) {
//            int userId = currentUserOptional.get().getId();
//            List<ReaderDTO> readers = readerService.findAll().stream()
//                    .filter(reader -> reader.getId() != userId)
//                    .collect(Collectors.toList());
//            model.addAttribute("model", viewModel);
//            model.addAttribute("form", new CreateFriendListForm(0));
//            model.addAttribute("readers", readers);
//            return "friend/add";
//        } else {
//            model.addAttribute("errorMessage", "Пользователь не найден.");
//            return "error/userNotFound"; // Redirect to a user not found error page
//        }
//    }

    @GetMapping("/add")
    public String showAddFriendForm(Model model, Principal principal) {
        String loggedInEmail = principal.getName();
        Reader loggedInReader = readerService.findByEmailReader(loggedInEmail);
        List<Reader> availableReaders = readerService.findAllReadersExcept(loggedInReader.getId());
        var viewModel = new BaseViewModel("Бронирование места");
        model.addAttribute("model", viewModel);  // Убедитесь, что в шаблоне используется правильное поле
        model.addAttribute("readerId", loggedInReader.getId());
        model.addAttribute("availableReaders", availableReaders);
        return "friend/add";
    }
    @PostMapping("/add")
    public String addFriend(@RequestParam int friendReaderId, Model model, Principal principal) {
        try {
            // Fetch the logged-in user's readerId from the Principal (Spring Security)
            String loggedInEmail = principal.getName(); // Assuming email is used for authentication

            // Find the logged-in Reader by email or other user-specific identifier
            Reader loggedInReader = readerService.findByEmailReader(loggedInEmail);

            // Call service method to add friend
            friendService.addFriend(loggedInReader.getId(), friendReaderId);

            // Create a view model for the page
            var viewModel = new BaseViewModel("Бронирование места");
            model.addAttribute("model", viewModel);

            // Add the readerId to the model for use in the redirect
            model.addAttribute("readerId", loggedInReader.getId());

            // Redirect to the list of friends page using logged-in readerId
            LOG.log(Level.INFO, loggedInReader + "friend add" + friendReaderId);
            return "redirect:/friend/" + loggedInReader.getId();
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "friend/add"; // Return to the add friend page in case of error
        }
    }

    @Override
    @GetMapping("/{readerId}")
    public String getAllFriends(@PathVariable int readerId, Model model, Principal principal) {
        try {
            // Fetch the logged-in user's readerId from the Principal
            String loggedInEmail = principal.getName(); // Assuming email is used for authentication
            Reader loggedInReader = readerService.findByEmailReader(loggedInEmail);

            // Check if the logged-in user is trying to view their own friends
            if (loggedInReader.getId() != readerId) {
                throw new RuntimeException("You can only view your own friends.");
            }

            // Fetch the list of friends for the logged-in user
            List<FriendDTO> friends = friendService.findAllFriendsByReader(loggedInReader.getId());

            // Add attributes to the model for rendering the view
            model.addAttribute("readerId", loggedInReader.getId());
            model.addAttribute("friends", friends);

            return "friend/list"; // View page for friends
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "error"; // Error page
        }
    }

    @Override
    public String addFriend(int readerId, int friendReaderId, Model model) {
        return null;
    }


//    @PostMapping("/add")
//    public String addFriend(@Valid @ModelAttribute("form") CreateFriendListForm form, BindingResult bindingResult,
//                            Model model) {
//        if (bindingResult.hasErrors()) {
//            // В случае ошибок, передаем список читателей и форму обратно на страницу добавления
//            List<ReaderDTO> readers = readerService.findAll();  // Можно добавить фильтрацию в случае ошибок
//            var viewModel = new FriendListCreateViewModel(createBaseViewModel("Добавление друга"));
//
//            model.addAttribute("model", viewModel);
//            model.addAttribute("form", form);
//            model.addAttribute("readers", readers); // Передаем список читателей снова
//            return "friend/add";  // Возвращаемся на страницу добавления друга
//        }
//
//
//    // Логика добавления друга
//        friendService.addFriend(form.readerId());
//
//        // Перенаправляем пользователя на страницу списка друзей
//        return "redirect:/friend";  // Перенаправление на страницу со списком друзей
//    }
//










//    @Override
//    @PostMapping("/add")
//    public String addFriend(int id, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            var viewModel = new FriendListCreateViewModel(
//                    createBaseViewModel("Добавить друга")
//            );
//            int friendId = friendService.addFriend(id);
//            model.addAttribute("model", viewModel);
//        }
//        return "friend";
//    }

//    @Override
//    @GetMapping()
//    public String listFriends(@ModelAttribute("form") FriendListSearchForm form, Model model) {
//        // Получаем текущего пользователя
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUsername = authentication.getName();
//        Optional<Reader> currentUserOptional = readerService.findByEmail(currentUsername);
//
//        if (currentUserOptional.isEmpty()) {
//            model.addAttribute("error", "User not found. Please log in.");
//            return "friend/list"; // Или редирект на страницу входа
//        }
//
//        Reader currentUser = currentUserOptional.get();
//
//        // Применяем фильтрацию для друзей текущего пользователя
//        var searchTerm = form.searchTerm() != null ? form.searchTerm() : "";
//
//        // Получаем всех друзей текущего пользователя
//        List<FriendDTO> allFriends = friendService.findAllFriendsByReader(currentUser);
//
//        // Фильтруем по имени или фамилии, если поисковый запрос был передан
//        List<FriendDTO> filteredFriends = allFriends.stream()
//                .filter(friend -> friend.getFirstName().toLowerCase().contains(searchTerm.toLowerCase())
//                        || friend.getLastName().toLowerCase().contains(searchTerm.toLowerCase()))
//                .collect(Collectors.toList());
//
//        var friendsViewModels = filteredFriends.stream()
//                .map(friend -> new FriendViewModel(friend.getId(), friend.getFirstName(), friend.getLastName()))
//                .collect(Collectors.toList());
//
//        var viewModel = new FriendListViewModel(
//                createBaseViewModel("Список друзей"),
//                friendsViewModels,
//                filteredFriends.size(),
//                currentUser.getFirstName() + " " + currentUser.getLastName()
//        );
//
//        model.addAttribute("model", viewModel);
//        model.addAttribute("form", form);
//
//        return "friend/list"; // Возвращаем представление с фильтром
//    }
    @Override
    @GetMapping("jjj")
    public String details(int id, Model model) {
        var friendList = friendService.find(id);
        var viewModel = new FriendListDetailsViewModel(
                createBaseViewModel("Детали списка друзей"),
                new FriendViewModel(friendList.getId(),
                        friendList.getFirstName(),
                        friendList.getLastName())
        );
        model.addAttribute("model", viewModel);
        return "friend/details";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        friendService.deleteFriend(id);
        LOG.log(Level.INFO, "Delete friend");
        return "redirect:/friend/add";
    }

//    @PostMapping("/addFriend")
//    public String addFriend() {
//            // Вызываем метод сервиса для добавления друга
//            int friendId = friendService.addFriend(id);
//            model.addAttribute("message", "Друг успешно добавлен с ID: " + friendId);
//         // перенаправляем на тот же шаблон или другой
//    }


//    @GetMapping("/")
//    public String top(Model model, Principal principal) {
//        // Получаем текущего пользователя (в данном случае предполагаем, что это объект Reader)
//        Reader currentUser = readerService.getCurrentUser(principal);
//
//        // Создаем базовую модель для отображения
//        var viewModel = new ReviewCreateViewModel(createBaseViewModel("Мнение друзей"));
//
//        // Получаем топ-3 книги по среднему рейтингу друзей
//        List<Books> topBooks = reviewService.findTop3BooksByAverageRatingByFriends(currentUser);
//
//        // Добавляем атрибуты в модель для Thymeleaf
//        model.addAttribute("topBooks", topBooks);
//        model.addAttribute("model", viewModel);
//
//        // Возвращаем имя шаблона
//        return "review/reviewTop"; // Шаблон Thymeleaf
//    }

}

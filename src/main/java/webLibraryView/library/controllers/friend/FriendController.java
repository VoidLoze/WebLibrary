package webLibraryView.library.controllers.friend;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webLibraryView.library.controllers.BaseController;

import java.security.Principal;

@RequestMapping("/friendList")
public interface FriendController extends BaseController {

//    @GetMapping("/add")
//    String createForm(Model model);
//    @PostMapping("/add")
//    String addFriend(@Valid @ModelAttribute("form") CreateFriendListForm form, BindingResult bindingResult,
//                     Model model);
//    @GetMapping
//    String listFriends(
//            @ModelAttribute("form") FriendListSearchForm form,
//            Model model
//    );

    @GetMapping("/{readerId}")
    String getAllFriends(@PathVariable int readerId, Model model, Principal principal);

    @PostMapping("/{readerId}/add")
    String addFriend(@PathVariable int readerId,
                     @RequestParam int friendReaderId, Model model);

    @GetMapping("/{id}")
    String details(
            @PathVariable int id,
            Model model
    );

    @PostMapping("/{id}/delete")
    String delete(@PathVariable Integer id);
}

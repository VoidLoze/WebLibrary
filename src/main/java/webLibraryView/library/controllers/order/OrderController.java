package webLibraryView.library.controllers.order;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.BaseController;

@RequestMapping("/orders")
public interface OrderController extends BaseController {
    @GetMapping("/create")
    String createForm(Model model);

//    @PostMapping("/create")
//    String create(
//            @Valid @ModelAttribute("form") CreateOrderForm form,
//            BindingResult bindingResult,
//            Model model);

//    @GetMapping
//    String listOrders(
//            @ModelAttribute("form") OrderSearchForm form,
//            Model model
//    );

    @GetMapping("/{id}")
    String details(
            @PathVariable int id,
            Model model
    );
}

package kata.academy.project.controller;

import kata.academy.project.model.UserInDto;
import kata.academy.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getListOfUsers(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                 @RequestParam(required = false, defaultValue = "5") Integer pageSize, ModelMap model) {
        model.addAttribute("users", userService.getAll(pageNumber, pageSize));
        model.addAttribute("totalCount", userService.getTotalCount());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        return "users";
    }

    @PostMapping
    public String create(@RequestBody UserInDto user, ModelMap model) {
        userService.create(user);
        model.addAttribute("totalCount", userService.getTotalCount());
        return "users";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable long id, @RequestBody UserInDto user, ModelMap model) {
        userService.update(id, user);
        model.addAttribute("totalCount", userService.getTotalCount());
        return "users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id, ModelMap model) {
        userService.delete(id);
        model.addAttribute("totalCount", userService.getTotalCount());
        return "users";
    }
}

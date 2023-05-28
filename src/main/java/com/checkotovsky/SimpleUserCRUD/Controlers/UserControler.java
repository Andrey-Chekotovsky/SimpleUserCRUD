package com.checkotovsky.SimpleUserCRUD.Controlers;

import com.checkotovsky.SimpleUserCRUD.Models.Role;
import com.checkotovsky.SimpleUserCRUD.Models.User;
import com.checkotovsky.SimpleUserCRUD.dao.UserDAO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

@Controller
@EnableWebMvc
@RequestMapping("/user")
public class UserControler {
    private UserDAO userDAO;

    public UserControler(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @GetMapping("/list")
    public String showAll(Model model)
    {
        model.addAttribute("users", userDAO.selectAll());
        return "user/showALL";
    }
    @GetMapping("/show/{id}")
    public String userInfo(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("user", userDAO.select(id));
        return "user/show";
    }

    @GetMapping("/add")
    public String createUser(Model model)
    {
        model.addAttribute("user", new User());
        return "user/addUser";
    }

    @PostMapping()
    public String addPerson (@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "user/addUser";
        user.setRole(Role.Customer);
        userDAO.insert(user);
        return "redirect:/user/list";
    }

    @GetMapping("/{id}/redact")
    public String redactUser(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("user", userDAO.select(id));
        return "user/edit";
    }


//    @RequestMapping(value = "/{id}",
//            method={RequestMethod.PATCH, RequestMethod.POST})
    @PatchMapping(name = "/{id}")
    public String changeUser(@PathVariable("id") int id, @ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "user/edit";
        userDAO.update(user, id);
        return "redirect:/user/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id)
    {
        userDAO.delete(id);
        return "redirect:/user/list";
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
//        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    ResponseEntity<String> handleConstraintViolationPasswordException(ConstraintViolationException e) {
//        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }

}

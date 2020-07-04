package org.example.controller

import org.example.entity.User
import org.example.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RegistrationController {
    @Autowired
    private val userService: UserService? = null

    @GetMapping("/registration")
    fun registration(model: Model): String {
        model.addAttribute("userForm", User())
        return "registration"
    }

    @PostMapping("/registration")
    fun addUser(@ModelAttribute("userForm") userForm: User?, bindingResult: BindingResult, model: Model): String {

        if (bindingResult.hasErrors()) {
            return "registration"
        }
        if (userForm != null) {
            if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
                model.addAttribute("passwordError", "Пароли не совпадают")
                return "registration"
            }
        }
        if (!userForm?.let { userService!!.saveUser(it) }!!) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует")
            return "registration"
        }
        return "redirect:/"
    }
}

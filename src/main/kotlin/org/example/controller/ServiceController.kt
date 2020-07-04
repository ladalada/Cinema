package org.example.controller

import org.example.entity.Services
import org.example.service.BasketService
import org.example.service.CinemaService
import org.example.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid

@Controller
class ServiceController {

    @Autowired
    var cinemaService: CinemaService? = null

    @Autowired
    var basketService: BasketService? = null

    @Autowired
    var userService: UserService? = null

    @GetMapping("/services")
    fun getProducts(model: Model): String {
        model.addAttribute("services", cinemaService!!.getServices())
        model.addAttribute("admin", true)
        return "services"
    }

    @GetMapping("/add_service")
    fun addProduct(): String{
        return "newservice"
    }

    @GetMapping("/remove_service")
    fun removeProduct(@RequestParam(required = true, defaultValue = "") serviceId: Long?): String {
        cinemaService!!.removeService(serviceId!!)
        return "redirect:services"
    }

    @GetMapping("/to_basket")
    fun addProductToBasket(@RequestParam(required = true, defaultValue = "") serviceId: Long?, userName: String, model: Model): String {
        val user = userService!!.findUserByName(userName!!)
        val product = cinemaService!!.getService(serviceId)
        basketService!!.addProduct(product, user!!)
        return "redirect:services"
    }

    @PostMapping("/addService")
    fun addInformation(@Valid @ModelAttribute("service") service: Services?): String {
        cinemaService!!.addService(service!!)
        return "redirect:services"
    }

    @PostMapping("/change_service")
    fun changeCost(@Valid @ModelAttribute("new_cost") service: Services): String {
        cinemaService!!.addService(service)
        return "redirect:services"
    }

    @GetMapping("/movie")
    fun watchMovie(@RequestParam(required = true, defaultValue = "") movieName: String?, movieGenre: String?, userName: String, model: Model): String {
        model.addAttribute("username", userName)
        model.addAttribute("movieName", movieName)
        model.addAttribute("movieGenre", movieGenre)
        return "movie"
    }

}
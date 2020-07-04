package org.example.controller

import org.example.service.BasketService
import org.example.service.OrderService
import org.example.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AdminController {

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val orderService: OrderService? = null

    @Autowired
    private val basketService: BasketService? = null

    @GetMapping("/users")
    fun userList(model: Model): String {
        model.addAttribute("allUsers", userService!!.allUsers())
        return "users"
    }

    @GetMapping("/remove_user")
    fun deleteUser(@RequestParam(required = true, defaultValue = "") userId: Long?): String {

        var allUserOrders = orderService!!.getOrdersByUserId(userId!!)
        if (allUserOrders != null) {
            for (userOrder in allUserOrders) {
                this.orderService!!.removeOrder(userOrder.id)
            }
        }

        var userBasketId = basketService!!.getBasketIdByUserId(userId!!)
        if (userBasketId != null) {
            this.basketService!!.clearBasket(userBasketId)
        }

        userService!!.deleteUser(userId!!)
        return "redirect:/users"
    }

    @GetMapping("/remove_users_role")
    fun deleteUsersRole(@RequestParam(required = true, defaultValue = "") userId: Long?,
                        @RequestParam(required = true, defaultValue = "") roleId: Long?): String {
        userService!!.deleteRole(userId!!,roleId!!)
        return "redirect:/users"
    }

    @GetMapping("/admin/gt/{userId}")
    fun gtUser(@PathVariable("userId") userId: Long?, model: Model): String {
        model.addAttribute("allUsers", userService!!.usergtList(userId))
        return "admin"
    }

    @GetMapping("/make_manager")
    fun makeManager(@RequestParam(required = true, defaultValue = "") userId:Long?): String {
        val user = userService!!.findUserById(userId!!)
        userService.addManager(user!!)
        return "redirect:/users"
    }
}
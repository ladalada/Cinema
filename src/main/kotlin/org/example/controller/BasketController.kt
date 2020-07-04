package org.example.controller

import org.example.service.BasketService
import org.example.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class BasketController {

    @Autowired
    private val basketService: BasketService? = null

    @Autowired
    private val orderService: OrderService? = null

    @GetMapping("/basket")
    fun getBasket(@RequestParam(required = true, defaultValue = "") userName: String?,
                  model: Model?): String {
        model!!.addAttribute("basket", basketService!!.getBasketByUserName(userName!!))
        return "basket"
    }

    @GetMapping("/clear")
    fun clearBasket(@RequestParam(required = true, defaultValue = "") backetId:Long?): String {
        this.basketService!!.clearBasket(backetId!!)
        return "basket"
    }

    @GetMapping("/clear_order")
    fun clearOrder(@RequestParam(required = true, defaultValue = "") basketId:Long?):String{
        this.clearBasket(basketId)
        return "redirect:/"
    }

    @GetMapping("/make_order")
    fun makeOrder(@RequestParam(required = true, defaultValue = "") basketId: Long?,
                  model: Model?): String {
        val bs = this.basketService!!.getBasket(basketId)
        this.orderService!!.makeOrder(bs!!)
        this.clearBasket(bs.id)
        return "redirect:/"
    }
}
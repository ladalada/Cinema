package org.example.controller

import org.example.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class OrderController{

    @Autowired
    private var orderService: OrderService? = null

    @GetMapping("/orders")
    fun ordersList(model: Model):String {
        model.addAttribute("orders", orderService!!.getAllOrders())
        model.addAttribute("type","all")
        return "orders"
    }

    @GetMapping("/myorders")
    fun getUserOrdersList(@RequestParam(required = true, defaultValue = "") userName: String?,
                          model: Model?): String {
        model!!.addAttribute("orders", orderService!!.getOrdersByUserName(userName!!))
        return "orders"
    }

    //удаление заказа
    @GetMapping("/order_r")
    fun removeOrder(@RequestParam(required = true, defaultValue = "") orderId: Long?): String {
        this.orderService!!.removeOrder(orderId!!)
        return "redirect:/orders"
    }

}
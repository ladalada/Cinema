package org.example.service

import org.example.entity.Basket
import org.example.entity.Order
import org.example.repository.OrderRepository
import org.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService{

    @Autowired
    private val orderRepository: OrderRepository? = null

    @Autowired
    private val userRepository: UserRepository? = null

    //список всех заказов
    fun getAllOrders(): MutableList<Order?>? {

        return orderRepository!!.findAll()
    }

    fun getOrdersByUserName(userName: String): MutableList<Order>? {
        val userId = userRepository!!.findByUsername(userName)!!.id
        return orderRepository!!.findAllByUserId(userId)
    }

    fun getOrdersByUserId(userId: Long): MutableList<Order>? {
        return orderRepository!!.findAllByUserId(userId)
    }

    fun getOrder(id: Long): Optional<Order?>? {
        return orderRepository!!.findById(id)
    }

    fun removeOrder(id: Long?) {
        orderRepository!!.deleteById(id)
    }

    fun makeOrder(basket: Basket){
        val date="today"
        val order = Order(date)
        order.setUser(basket.getUser())
        val services = mutableSetOf<org.example.entity.Service>()
        for (item in basket.getService()){
            services.add(item)
        }
        order.setService(services)
        this.orderRepository!!.save(order)
    }
}
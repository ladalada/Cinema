package org.example.service

import org.example.entity.Basket
import org.example.entity.Services
import org.example.entity.User
import org.example.repository.BasketRepository
import org.example.repository.ServiceRepository
import org.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BasketService {

    @Autowired
    var basketRepository: BasketRepository?=null

    @Autowired
    private val userRepository: UserRepository?=null

    @Autowired
    var serviceRepository: ServiceRepository?=null

    //добавление фильма в корзину
    fun addProduct(service: Services, user: User) {

        var basket: Basket? = null
        val serviceToBasket: org.example.entity.Service? = org.example.entity.Service()
        var services: MutableSet<org.example.entity.Service>? = null

        serviceToBasket!!.time = 13
        serviceToBasket.price = service.cost!!
        serviceToBasket.hall = 1
        serviceToBasket.setServices(service)
        this.serviceRepository!!.save(serviceToBasket)

        //корзина уже заполнена
        if (this.basketRepository!!.findByUserId(user.id!!) != null) {
            basket = this.basketRepository!!.findByUserId(user.id!!)
            services = basket!!.getService()
        }
        //только создаем корзину
        else {
            services = mutableSetOf<org.example.entity.Service>()
        }

        services.add(serviceToBasket)

        basket = Basket(user.id, user, services)

        this.basketRepository!!.save(basket)
    }

    //очистка корзины
    fun clearBasket(id: Long) {
        this.basketRepository!!.deleteById(id)
    }

    fun getBasket(id: Long?): Basket? {
        if (this.basketRepository!!.findById(id).isPresent){
            return this.basketRepository!!.findById(id!!).get()
        }
        return null
    }

    fun getBasketIdByUserId(userId: Long): Long? {
        return basketRepository!!.findByUserId(userId)?.id
    }

    fun getBasketByUserName(userName: String): Basket? {
        val userId = userRepository!!.findByUsername(userName)!!.id
        val basket = basketRepository!!.findByUserId(userId)
        return basketRepository!!.findByUserId(userId)
    }

}
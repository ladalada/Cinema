package org.example.entity

// Корзина пользователя
// Содержит MutableSet<Service>
// К текущему списку наименований в корзине
// добавляется новое добавленное в корзину

import javax.persistence.*

@Entity
@Table(name="basket")
class Basket {

    @Id
    var id: Long?=null

    @ManyToOne
    private var user: User? = null

    @OneToMany
    private var service: MutableSet<Service>? = mutableSetOf<Service>()

    constructor() {}

    constructor(id: Long?, user: User?, service: MutableSet<Service>?) {
        this.id = id
        this.user = user
        this.service = service
    }

    fun setUser(user: User) {
        this.user = user;
    }

    fun getUser(): User {
        return this.user!!;
    }

    fun setService(service: Service) {
        this.service!!.add(service)
    }

    fun getService(): MutableSet<Service> {
        return this.service!!
    }

}
package org.example.entity

// Заказ пользователя может содержать
// несколько наименований MutableSet<Service>

import javax.persistence.*

@Entity
@Table(name="user_order")
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    private var date: String? = null
    @ManyToOne
    private var user: User? = null

    @ManyToMany
    private var service: MutableSet<Service>? = mutableSetOf<Service>()

    constructor(){}

    constructor(date:String){
        this.date = date
    }

    constructor(id: Long?, date: String?, user: User?, service: MutableSet<Service>?) {
        this.id = id
        this.date = date
        this.user = user
        this.service = service
    }


    fun setDate(date: String) {
        this.date = date
    }

    fun getDate(): String {
        return this.date!!
    }

    fun setUser(user: User) {
        this.user = user;
    }

    fun getUser(): User {
        return this.user!!
    }

    fun setService(service: MutableSet<Service>) {
        this.service = service
    }

    fun getService(): MutableSet<Service> {
        return this.service!!
    }

}
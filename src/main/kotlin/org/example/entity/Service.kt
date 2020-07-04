package org.example.entity

// Фильм с временем и залом
// Фильм, который ложится в корзину пользователя при добавлении

import javax.persistence.*

@Entity
@Table(name = "service")
class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var price: Double? = null
    var time: Int? = null
    var hall: Int? = null

//    сам фильм из каталога
    @OneToOne
    private var services: Services? = null

    constructor() {}

    constructor(id: Long?, price: Double?, time: Int?, hall: Int?, services: Services?) {
        this.id = id
        this.price = price
        this.time = time
        this.hall = hall
        this.services = services
    }

    fun getServices(): Services {
        return this.services!!
    }

    fun setServices(services: Services?) {
        this.services = services
    }

    fun getPrice(): Double {
        return this.price!!
    }

    fun getTime(): Int {
        return this.time!!
    }

    fun getHall(): Int {
        return this.hall!!
    }

    fun getServiceGenre(): String {
        return this.services!!.getServicesGenre()
    }

    fun getServiceName(): String {
        return this.services!!.getServicesName()
    }

    fun getServiceNameAndGenre(): List<String> {
        var movieName = getServiceName()
        var movieGenre = getServiceGenre()
        return listOf(movieName, movieGenre)
    }

}
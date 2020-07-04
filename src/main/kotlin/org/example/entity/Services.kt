package org.example.entity

// Каждый фильм из каталога
// Его пользователь выбирает и добавляет в корзину

import javax.persistence.*

@Entity
@Table(name = "services")
class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var cost: Double? = null
    var genre: String? = null

    constructor() {}

    constructor(id: Long?, name: String?, cost: Double?, genre: String?) {
        this.id = id
        this.name = name
        this.cost = cost
        this.genre = genre
    }

    fun getServicesGenre(): String {
        return this.genre!!
    }

    fun getServicesName(): String {
        return this.name!!
    }

}
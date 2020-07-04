package org.example.repository

import org.example.entity.Basket
import org.springframework.data.jpa.repository.JpaRepository

interface BasketRepository : JpaRepository<Basket?, Long?> {

    fun findByUserId(userId: Long?): Basket?
}
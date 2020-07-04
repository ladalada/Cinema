package org.example.repository

import org.example.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order?, Long?> {

    fun findAllByUserId(userId: Long?): MutableList<Order>?

    fun findAllByServiceId(serviceId: Long?): MutableList<Order>?

    fun deleteByServiceId(serviceId: Long?)

}
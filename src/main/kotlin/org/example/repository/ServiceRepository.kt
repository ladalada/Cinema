package org.example.repository

import org.example.entity.Service
import org.springframework.data.jpa.repository.JpaRepository

interface ServiceRepository : JpaRepository<Service?, Long?> {

    fun findServiceByServicesId(id: Long?): Service

    fun deleteByServicesId(id: Long?)

    fun findServiceListByServicesId(id: Long?): List<Service>

}
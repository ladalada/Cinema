package org.example.repository

import org.example.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User?, Long?> {

    fun findByUsername(username: String?): User?
}
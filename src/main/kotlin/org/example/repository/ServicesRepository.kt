package org.example.repository

import org.example.entity.Services
import org.springframework.data.jpa.repository.JpaRepository

interface ServicesRepository : JpaRepository<Services?, Long?>

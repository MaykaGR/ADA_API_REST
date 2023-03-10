package com.dam.api.repositories

import com.dam.api.models.Sessions
import org.springframework.data.repository.CrudRepository
import java.sql.Date

interface SessionsRepository: CrudRepository<Sessions, Long> {
}
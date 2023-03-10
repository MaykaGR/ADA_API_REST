package com.dam.api.services

import com.dam.api.commons.GenericServiceImpl
import com.dam.api.models.Peliculas
import com.dam.api.models.Sessions
import com.dam.api.repositories.SessionsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.sql.Date

@Service
class SessionsServiceImpl: SessionsServiceAPI, GenericServiceImpl<Sessions, Date>()  {
    @Autowired
    lateinit var sessionsRepository: SessionsRepository

    override val dao: CrudRepository<Sessions, Date>
        get() = sessionsRepository
}
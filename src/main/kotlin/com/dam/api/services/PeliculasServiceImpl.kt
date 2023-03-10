package com.dam.api.services

import com.dam.api.commons.GenericServiceImpl
import com.dam.api.models.Peliculas
import com.dam.api.repositories.PeliculasRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service


@Service
class PeliculasServiceImpl: PeliculasServiceAPI, GenericServiceImpl<Peliculas, Long>() {

    @Autowired
    lateinit var peliculasRepository: PeliculasRepository

    override val dao: CrudRepository<Peliculas, Long>
        get() = peliculasRepository

}
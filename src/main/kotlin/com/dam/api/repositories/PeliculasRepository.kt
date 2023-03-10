package com.dam.api.repositories

import com.dam.api.models.Peliculas
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PeliculasRepository: CrudRepository<Peliculas, Long> {
}
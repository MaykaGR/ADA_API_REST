package com.dam.api.services

import com.dam.api.commons.GenericServiceImpl
import com.dam.api.models.Usuarios
import com.dam.api.repositories.UsuariosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class UsuariosServiceImpl: UsuariosServiceAPI, GenericServiceImpl<Usuarios, String>() {
    @Autowired
    lateinit var usuariosRepository: UsuariosRepository
    override val dao: CrudRepository<Usuarios, String>
        get() = usuariosRepository


}
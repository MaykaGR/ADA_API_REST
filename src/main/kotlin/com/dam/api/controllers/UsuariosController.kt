package com.dam.api.controllers

import com.dam.api.models.Usuarios
import com.dam.api.services.UsuariosServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
class UsuariosController {
    @Autowired
    lateinit var ser: UsuariosServiceImpl

    //URL -> /api/users/
    @GetMapping("/")
    fun getAll(): ResponseEntity<MutableList<Usuarios>> {
        var usuarios = ser.all
        return ResponseEntity(usuarios, HttpStatus.OK)
    }

    @GetMapping("/{nick}")
    fun getOneUser(@PathVariable nick: String): ResponseEntity<Usuarios> {
        var user: Usuarios? = ser[nick]
        return ResponseEntity<Usuarios>(user, HttpStatus.OK)
    }

    @DeleteMapping("/{nick}")
    fun deleteOneUser(@PathVariable nick: String): ResponseEntity<String> {
        ser.delete(nick)
        var user = ser[nick]
        if(user==null){
            return ResponseEntity("Borrado", HttpStatus.OK)
        } else {
            return ResponseEntity("No borrado "+user.nick, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/")
    fun insertUser(@RequestBody user: Usuarios): ResponseEntity<String> {
        var producto = ser.save(user)
        if(producto!=null){
            return ResponseEntity("Insertado "+user.nick, HttpStatus.OK)
        } else{
            return ResponseEntity("Error", HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/{nick}")
    fun updateUsuario(@RequestBody user: Usuarios, @PathVariable nick: String): ResponseEntity<String> {
        var userU = ser[nick]
        if(userU!=null){
        userU = user
        var guardado = ser.save(userU)
        if(guardado!=null){
            return ResponseEntity("Cambiado usuario "+user.nick, HttpStatus.OK)
        } else{
            return ResponseEntity("Error", HttpStatus.BAD_REQUEST)
        }} else {
            return ResponseEntity("El usuario no existe", HttpStatus.NOT_FOUND)
        }
    }
}
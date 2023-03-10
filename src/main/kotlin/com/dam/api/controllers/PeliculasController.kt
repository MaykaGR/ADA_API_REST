package com.dam.api.controllers

import com.dam.api.models.Peliculas
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.dam.api.services.PeliculasServiceImpl


@RestController
@RequestMapping("/api/movies")
@CrossOrigin("*")
class PeliculasController {

    @Autowired
    lateinit var ser: PeliculasServiceImpl

    //URL -> /api/movies/
    @GetMapping("/")
    fun getAll(): ResponseEntity<MutableList<Peliculas>> {
        var peliculas = ser.all
        return ResponseEntity(peliculas,HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getOneFilm(@PathVariable id: String): ResponseEntity<Peliculas>{
        var idFilm: Long = id.toLong()
        var pelicula: Peliculas? = ser[idFilm]
        return ResponseEntity<Peliculas>(pelicula, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteOneFilm(@PathVariable id: String): ResponseEntity<String>{
        var idFilm: Long = id.toLong()
        ser.delete(idFilm)
        var film = ser[idFilm]
        if(film==null){
            return ResponseEntity("Borrado",HttpStatus.OK)
        } else {
            return ResponseEntity("No borrado "+film.title, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/")
    fun insertPelicula(@RequestBody film: Peliculas): ResponseEntity<String>{
        var pelicula = ser.save(film)
        if(pelicula!=null){
            return ResponseEntity("Insertado "+film.title,HttpStatus.OK)
        } else{
        return ResponseEntity("Error",HttpStatus.BAD_REQUEST)}
    }

    @PutMapping("/")
    fun updatePelicula(@RequestBody film: Peliculas): ResponseEntity<String>{
        var pelicula = ser.save(film)
        if(pelicula!=null){
            return ResponseEntity("Cambiada película con id "+film.id,HttpStatus.OK)
        } else{
            return ResponseEntity("Error",HttpStatus.BAD_REQUEST)}
    }


}
package com.dam.api.controllers

import com.dam.api.models.Sessions
import com.dam.api.services.SessionsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RestController
@RequestMapping("/api/v1/sessions")
@CrossOrigin("*")
class SessionsController {
    @Autowired
    lateinit var ser: SessionsServiceImpl

    //URL -> /api/sessions/
    @GetMapping("/")
    fun getAll(): ResponseEntity<MutableList<Sessions>> {
        var sessions = ser.all
        return ResponseEntity(sessions, HttpStatus.OK)
    }


    @GetMapping("/today")
    fun getTodaySessions(): ResponseEntity<MutableList<Sessions>> {
        var session = currentSession()
        return ResponseEntity(session, HttpStatus.OK)
    }

    private fun currentSession(): MutableList<Sessions>?{
        val today = LocalDate.now()
        val all = allSessions()
        val contador = (all?.size?: 1) -1
        val sesiones: MutableList<Sessions> = mutableListOf()
        for(i in 0..contador){
            if(all?.get(i)!!.date==today){
                sesiones.add(all[i])
            }
        }
        return sesiones
    }
    private fun allSessions(): MutableList<Sessions>?{
        val sessions = ser.all
        return sessions
    }

    @GetMapping("/sincetoday")
    fun allSinceToday(): ResponseEntity<MutableList<Sessions>> {
        val today = LocalDate.now()
        //println(today)
        val all = allSessions()?.sortedBy { it.date }
        /*if (all != null) {
            for(i in 0..all.size-1)
                println(all[i].date)
        }*/
        val contador = (all?.size?: 1) -1
        val sesiones: MutableList<Sessions> = mutableListOf()
        for(i in 0..contador){
            if(all?.get(i)!!.date.isEqual(today)||all?.get(i)!!.date.isAfter(today)){
                sesiones.add(all.get(i))
            }
        }
        return ResponseEntity(sesiones,HttpStatus.OK)
    }



    @DeleteMapping("/{id}")
    fun deleteOneSession(@PathVariable id: Long): ResponseEntity<String> {
        ser.delete(id)
        var session = ser[id]
        if(session==null){
            return ResponseEntity("Borrado", HttpStatus.OK)
        } else {
            return ResponseEntity("No borrado "+session.id, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/")
    fun insertSession(@RequestBody session: Sessions): ResponseEntity<String> {
        var sessio = ser.save(session)
        if(sessio!=null){
            return ResponseEntity("Insertado "+session.id, HttpStatus.OK)
        } else{
            return ResponseEntity("Error", HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/")
    fun updateSession(@RequestBody session: Sessions): ResponseEntity<String> {
        var sessio = ser.save(session)
        if(sessio!=null){
            return ResponseEntity("Cambiada sesión con id "+session.id, HttpStatus.OK)
        } else{
            return ResponseEntity("Error", HttpStatus.BAD_REQUEST)
        }
    }

}
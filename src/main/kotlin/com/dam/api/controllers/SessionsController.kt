package com.dam.api.controllers

import com.dam.api.models.Sessions
import com.dam.api.services.SessionsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/api/sessions")
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
    fun getOneSession(): ResponseEntity<Sessions> {
        val today: java.util.Date = java.util.Date()
        val date = java.sql.Date(today.day,today.month,today.year)
        var session: Sessions? = ser[date]
        return ResponseEntity<Sessions>(session, HttpStatus.OK)
    }

    private fun currentSession(): Sessions?{
        val today: java.util.Date = java.util.Date()
        val date = java.sql.Date(today.day,today.month,today.year)
        val session: Sessions? = ser[date]
        return session
    }
    private fun allSessions(): MutableList<Sessions>?{
        val sessions = ser.all
        return sessions
    }

    @GetMapping("/sincetoday")
    fun allSinceToday(): ResponseEntity<MutableList<Sessions>> {
        val sessionToday = currentSession()?.date
        val all = allSessions()?.sortedBy { it.date }
        val contador = (all?.size?: 1) -1
        val sesiones: MutableList<Sessions> = mutableListOf()
        for(i in 0..contador){
            if(all?.get(i)!!.date==sessionToday){
                sesiones.addAll(sesiones.subList(fromIndex = i, toIndex = contador))
            }
        }
        return ResponseEntity(sesiones,HttpStatus.OK)
    }



    @DeleteMapping("/{date}")
    fun deleteOneSession(@PathVariable date: java.sql.Date): ResponseEntity<String> {
        ser.delete(date)
        var session = ser[date]
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
            return ResponseEntity("Cambiado producto con id "+session.id, HttpStatus.ACCEPTED)
        } else{
            return ResponseEntity("Error", HttpStatus.BAD_REQUEST)
        }
    }

}
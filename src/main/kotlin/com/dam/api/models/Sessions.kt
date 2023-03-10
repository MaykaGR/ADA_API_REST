package com.dam.api.models
import jakarta.persistence.*
import java.sql.Date
import java.time.LocalDate

@Entity
@Table(name = "sessions")
class Sessions(
    //@ManyToOne(cascade = [CascadeType.ALL])
    //@JoinColumn(name="movie_id")
    @Column(name="movie_id")
    var movie_id: Int,
    @Column(name = "room_id")
    var room_id: Int,
    @Column(name = "date")
    var date: LocalDate,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long) {
}
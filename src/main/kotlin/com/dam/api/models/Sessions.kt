package com.dam.api.models
import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "sessions")
class Sessions(
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name="movie_id")
    var movie_id: Peliculas,
    @Column(name = "room_id")
    var room_id: Int,
    @Id
    @Column(name = "date")
    var date: Date,
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long) {
}
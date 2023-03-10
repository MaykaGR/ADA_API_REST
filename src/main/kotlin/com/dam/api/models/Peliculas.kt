package com.dam.api.models

import jakarta.persistence.*

@Entity
@Table(name = "peliculas")
class Peliculas (
    @Column(name = "title")
    var title: String,
    @Column(name= "director")
    var director: String,
    @Column(name = "time")
    var time: String,
    @Column(name = "trailer")
    var trailer: String,
    @Column(name = "poster_image")
    var poster_image: String,
    @Column(name = "screenshot")
    var screenshot: String,
    @Column(name = "synopsis")
    var synopsis: String,
    @OneToMany(mappedBy = "movie_id", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val sessions: MutableSet<Sessions> = mutableSetOf(),
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long
    ){

}
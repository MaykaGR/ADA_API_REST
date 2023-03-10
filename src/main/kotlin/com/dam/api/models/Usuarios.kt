package com.dam.api.models

import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
class Usuarios(
    @Id
    @Column(name = "nick")
    var nick: String,
    @Column(name = "email")
    var email: String,
    @Column(name = "password")
    var password: String,
    @Column(name = "profilePicture")
    var profilePicture: String,
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long) {
}
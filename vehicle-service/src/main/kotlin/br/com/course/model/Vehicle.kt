package br.com.course.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "vehicle")
data class Vehicle(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val model: String,
    val brand: String,
    val licensePlate: String
)

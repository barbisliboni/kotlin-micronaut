package br.com.course.model

@NoArg
data class Vehicle(
    val id: Long,
    val model: String,
    val brand: String,
    val licensePlate: String
)

package br.com.course.repository

import br.com.course.model.Vehicle
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface VehicleRepository: JpaRepository<Vehicle, Long> {
}
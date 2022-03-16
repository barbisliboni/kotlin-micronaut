package br.com.course.service

import br.com.course.model.Vehicle
import br.com.course.repository.VehicleRepository
import jakarta.inject.Singleton

@Singleton //the container creates a single instance and injects the same instance at all injection points that require it
class VehicleService(
    private val vehicleRepository: VehicleRepository
) {
    fun create(vehicle: Vehicle): Vehicle{
        return vehicleRepository.save(vehicle)
    }

    fun findById(id: Long): Vehicle{
        return vehicleRepository.findById(id).get() //using .get() here isn't a good practice
    }
}
package br.com.course.controller

import br.com.course.model.Vehicle
import br.com.course.service.VehicleService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*


@Controller("/vehicles")
class VehicleController(
    private val vehicleService: VehicleService
) {
    @Post
    fun create(@Body vehicle: Vehicle): HttpResponse<Vehicle> { //@Body cause it will return the response in the body response
        return HttpResponse.created(vehicleService.create(vehicle))
    }

    @Get("/{id}")
    fun findById(@PathVariable id: Long): Vehicle { //@PathVariable cause it will receive the id in the url
        return vehicleService.findById(id)
    }
}
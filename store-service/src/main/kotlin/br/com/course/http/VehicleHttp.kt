package br.com.course.http

import br.com.course.dto.output.Vehicle
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client
import io.micronaut.retry.annotation.CircuitBreaker

@Client(id = "http://localhost:8080") //where the vehicle microservice is running
@CircuitBreaker
interface VehicleHttp {
    @Get("/vehicles/{id}")
    fun findById(@PathVariable id: Long): Vehicle

}
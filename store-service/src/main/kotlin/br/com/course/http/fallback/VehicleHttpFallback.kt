package br.com.course.http.fallback

import br.com.course.config.Connection
import br.com.course.dto.output.Vehicle
import br.com.course.http.VehicleHttp
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.retry.annotation.Fallback
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Fallback
class VehicleHttpFallback(
    private val objectMapper: ObjectMapper
): VehicleHttp {
    override fun findById(id: Long): Vehicle {
        var jedis = Connection.getConnection()
        val vehicleJSON = jedis.get(id.toString())
        val vehicle = objectMapper.readValue(vehicleJSON, Vehicle::class.java)

        return vehicle
    }
}
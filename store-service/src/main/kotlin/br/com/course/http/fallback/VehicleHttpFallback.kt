package br.com.course.http.fallback

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
        val jedisPool = JedisPool(JedisPoolConfig(), "127.0.0.1", 6379 ) //JedisPool provides a pool of connections to Redis to reuse on demand
        val jedis = jedisPool.resource
        val vehicleJSON = jedis.get(id.toString())
        val vehicle = objectMapper.readValue(vehicleJSON, Vehicle::class.java)

        return vehicle
    }
}
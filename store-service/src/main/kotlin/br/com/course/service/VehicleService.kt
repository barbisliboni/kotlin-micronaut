package br.com.course.service

import br.com.course.dto.output.Vehicle
import br.com.course.http.VehicleHttp
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.inject.Singleton
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Singleton
class VehicleService(
    private val vehicleHttp: VehicleHttp,
    private val objectMapper: ObjectMapper //getting the vehicle and transforming into a JSON
) {
    fun getVehicle(id: Long): Vehicle{
        val vehicle = vehicleHttp.findById(id)
        recordCache(vehicle)

        return vehicle
    }

    fun recordCache(vehicle: Vehicle){
        val jedisPool = JedisPool(JedisPoolConfig(), "127.0.0.1", 6379 )
        val jedis = jedisPool.resource
        var vehicleJSON = objectMapper.writeValueAsString(vehicle)
        jedis.set(vehicle.id.toString(), vehicleJSON) //recording on redis and always insert a key and a value
    }
}
package br.com.course.config

import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

object Connection {
    fun getConnection(): Jedis {
        val jedisPool = JedisPool(JedisPoolConfig(), "ms-redis", 6379 )
        return jedisPool.resource
    }
}
package com.nashtechglobal
package db.redis

import redis.clients.jedis.Jedis

// RedisService: This object provides simple methods for interacting with a Redis database.
object RedisService {
  // Redis client instance connected to localhost on default port 6379
  private val redisClient = new Jedis("localhost", 6379)

  // Writes a key-value pair to the Redis database
  def writeToRedis(key: String, value: String): Unit = {
    redisClient.set(key, value)
  }

  // Closes the Redis client to release resources
  def closeRedisClient(): Unit = {
    redisClient.close()
  }
}


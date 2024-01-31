package com.nashtechglobal
package db.redis

import redis.clients.jedis.Jedis

object RedisService {
  private val redisClient = new Jedis("localhost", 6379)

  def writeToRedis(key: String, value: String): Unit = {
    redisClient.set(key, value)
  }

  def closeRedisClient(): Unit = {
    redisClient.close()
  }
}


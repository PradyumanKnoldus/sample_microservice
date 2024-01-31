package com.nashtechglobal
package db.kafka

import bootstrap.JsonSerializer
import model.PizzaOrder

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import java.util.Properties

object KafkaProducer {
  private val kafkaTopic = "pizza-orders"
  private val bootstrapServers = "localhost:9092"

  private val producerProps = new Properties()
  producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
  producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
  producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

  private val producer = new KafkaProducer[String, String](producerProps)

  def sentToKafka(pizzaOrder: PizzaOrder): Unit = {
    val jsonOrder = JsonSerializer.toJson(pizzaOrder)
    producer.send(new ProducerRecord[String, String](kafkaTopic, jsonOrder))
  }

  def closeProducer(): Unit = {
    producer.close()
  }
}


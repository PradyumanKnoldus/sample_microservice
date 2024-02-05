package com.nashtechglobal
package db.kafka

import bootstrap.JsonSerializer
import model.PizzaOrder

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import java.util.Properties

// KafkaService: This object handles the interaction with Apache Kafka for sending PizzaOrder data.
object KafkaService {
  private val kafkaTopic = "pizza-orders"

  private val bootstrapServers = "localhost:9092"

  // Properties for configuring the Kafka producer
  private val producerProps = new Properties()
  producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
  producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
  producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

  // KafkaProducer instance for sending PizzaOrder data
  private val producer = new KafkaProducer[String, String](producerProps)

  // Sends a PizzaOrder Data to the Kafka topic in JSON format
  def sentToKafka(pizzaOrder: PizzaOrder): Unit = {
    val jsonOrder = JsonSerializer.toJson(pizzaOrder)
    producer.send(new ProducerRecord[String, String](kafkaTopic, jsonOrder))
  }

  // Closes the Kafka producer to release resources
  def closeProducer(): Unit = {
    producer.close()
  }
}


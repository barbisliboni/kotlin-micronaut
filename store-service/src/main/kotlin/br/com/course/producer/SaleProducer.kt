package br.com.course.producer

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient //to designate this interface as a client
interface SaleProducer {
    @Topic("ms-sales") //indicates which topics the ProducerRecord should be published to
    fun publishSale(@KafkaKey id: String, saleJSON: String) //defines two parameters: the Kafka key and the value
}
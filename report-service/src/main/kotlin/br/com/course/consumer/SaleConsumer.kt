package br.com.course.consumer

import br.com.course.model.Sale
import br.com.course.service.SaleService
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaListener(offsetReset = OffsetReset.EARLIEST) //offsetReset is used when consumer starts but there is no committed
class SaleConsumer(                             // offset for the assigned partition. In this case you can chose if you want
    private val objectMapper: ObjectMapper,      //to re-read all the messages from the beginning
    private val saleService: SaleService
) {
    @Topic("ms-sales")
    fun receiveSale(id: String, saleJSON: String){
        val sale = objectMapper.readValue(saleJSON, Sale::class.java)
        saleService.createSale(sale)
        println(sale)
    }
}
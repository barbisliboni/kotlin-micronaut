package br.com.course.service

import br.com.course.dto.input.SaleInput
import br.com.course.dto.output.Installment
import br.com.course.dto.output.Sale
import br.com.course.http.VehicleHttp
import br.com.course.producer.SaleProducer
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.inject.Singleton
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@Singleton
class SaleService(
    private val vehicleService: VehicleService,
    private val saleProducer: SaleProducer,
    private val objectMapper: ObjectMapper
) {
    fun makeSale(saleInput: SaleInput): Sale{
        val vehicle = vehicleService.getVehicle(saleInput.vehicle.toLong())
        var installments: List<Installment> = ArrayList<Installment>()
        var installmentPrice = saleInput.price.divide(saleInput.installments.toBigDecimal())
        var expireDate = LocalDate.now().plusMonths(1)

        for (i in 1..saleInput.installments){
            var installment = Installment(installmentPrice, expireDate.toString())
            installments = installments.plus(installment)
            expireDate = expireDate.plusMonths(1)
        }

        var sale = Sale(saleInput.client, vehicle, saleInput.price, installments)
        println(sale)
        saleConfirm(sale)
        return sale;
    }

    fun saleConfirm(sale: Sale){
        var saleJSON = objectMapper.writeValueAsString(sale)
        saleProducer.publishSale(UUID.randomUUID().toString(), saleJSON)
    }
}
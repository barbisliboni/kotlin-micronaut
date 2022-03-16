package br.com.course.service

import br.com.course.dto.input.SaleInput
import br.com.course.dto.output.Installment
import br.com.course.dto.output.Sale
import br.com.course.http.VehicleHttp
import jakarta.inject.Singleton
import java.time.LocalDate

@Singleton
class SaleService(
    private val vehicleHttp: VehicleHttp
) {
    fun makeSale(saleInput: SaleInput){
        val vehicle = vehicleHttp.findById(saleInput.vehicle.toLong())
        var installments: List<Installment> = ArrayList<Installment>()
        var installmentPrice = saleInput.price.divide(saleInput.installments.toBigDecimal())
        var expireDate = LocalDate.now().plusMonths(1)

        for (i in 1..saleInput.installments){
            var installment = Installment(installmentPrice, expireDate.toString())
            installments = installments.plus(installment)
            expireDate = expireDate.plusMonths(1)
        }

        var sale = Sale(saleInput.client, vehicle, saleInput.price, installments)
        print(sale)
    }
}
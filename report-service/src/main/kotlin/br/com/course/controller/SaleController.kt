package br.com.course.controller

import br.com.course.model.Sale
import br.com.course.service.SaleService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/sales")
class SaleController(
    private val saleService: SaleService
) {
    @Get
    fun getAll(): List<Sale>{
        return saleService.getAll()
    }
}
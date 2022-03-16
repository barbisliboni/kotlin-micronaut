package br.com.course.controller

import br.com.course.dto.input.SaleInput
import br.com.course.service.SaleService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/sales")
class SaleController(
    private val saleService: SaleService
) {
    @Post
    fun makeSale(@Body saleInput: SaleInput){
        saleService.makeSale(saleInput)
    }

}
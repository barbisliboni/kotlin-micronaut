package br.com.course.service

import br.com.course.model.Sale
import br.com.course.repository.SaleRepository
import jakarta.inject.Singleton

@Singleton
class SaleService(
    private val saleRepository: SaleRepository
) {
    fun createSale(sale: Sale){
        saleRepository.createSale(sale)
    }

    fun getAll(): List<Sale>{
        return saleRepository.getAll()
    }
}
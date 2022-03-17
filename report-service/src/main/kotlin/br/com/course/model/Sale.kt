package br.com.course.model

import java.math.BigDecimal

data class Sale(
    val client: String,
    val vehicle: Vehicle,
    val price: BigDecimal,
    val installments: List<Installment>
)
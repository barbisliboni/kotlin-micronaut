package br.com.course.dto.input

import java.math.BigDecimal

data class SaleInput(
    val client: String,
    val vehicle: Int,
    val price: BigDecimal,
    val installments: Int
)

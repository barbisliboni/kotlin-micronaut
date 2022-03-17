package br.com.course.model

import java.math.BigDecimal

data class Installment(
    val price: BigDecimal,
    var expireDate: String
)
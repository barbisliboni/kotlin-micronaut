package br.com.course.dto.output

import java.math.BigDecimal

data class Installment(
    val price: BigDecimal,
    var expireDate: String
)

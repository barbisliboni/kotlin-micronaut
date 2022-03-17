package br.com.course.model

import java.math.BigDecimal

@NoArg
data class Installment(
    val price: BigDecimal,
    var expireDate: String
)
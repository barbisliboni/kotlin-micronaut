package br.com.course.repository

import br.com.course.model.Sale
import com.mongodb.client.MongoClient
import com.mongodb.client.result.InsertOneResult
import jakarta.inject.Singleton

@Singleton
class SaleRepository(
    private val mongoClient: MongoClient
) {
    fun createSale(sale: Sale): InsertOneResult { //returning a mongo object
        return getConnection().insertOne(sale)
    }

    fun getConnection() = mongoClient
        .getDatabase("sales")
        .getCollection("sale", Sale::class.java)

    fun getAll(): List<Sale>{
        return getConnection().find().toList()
    }
}
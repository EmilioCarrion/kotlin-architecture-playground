package application.domain

import java.util.UUID

class Order(val id: UUID) {
    val lines = ArrayList<OrderLine>()

    fun addLine(line: OrderLine) {
        lines.add(line)
    }
}

data class OrderLine(val sku: Int, val quantity: Int)
package application.domain.ports.provided

import application.domain.Order
import java.util.*


fun interface CreateOrder {
    operator fun invoke(): Order
}

fun interface DeleteOrder {
    operator fun invoke(id: UUID)
}

fun interface AddLineToOrder {
    operator fun invoke(orderId: UUID, sku: Int, quantity: Int)
}

fun interface GetAllOrders {
    operator fun invoke(): List<Order>
}


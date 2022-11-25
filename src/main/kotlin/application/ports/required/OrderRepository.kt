package application.ports.required

import application.domain.Order
import java.util.UUID

interface OrderRepository {
    fun getOrderById(id: UUID): Order?
    fun createOrder(order: Order)
    fun deleteOrderWithId(id: UUID)
    fun getAllOrders(): List<Order>
    fun updateOrder(order: Order)
}
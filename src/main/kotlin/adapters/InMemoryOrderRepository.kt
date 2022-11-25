package adapters

import application.domain.Order
import application.domain.ports.required.OrderRepository
import java.util.UUID

class InMemoryOrderRepository : OrderRepository {
    private val orders = ArrayList<Order>()

    override fun getOrderById(id: UUID): Order? {
        orders.forEach {
            if (it.id == id) return it
        }
        return null
    }

    override fun createOrder(order: Order) {
        orders.add(order)
    }

    override fun deleteOrderWithId(id: UUID) {
        orders.remove(orders.first { it.id == id })
    }

    override fun getAllOrders(): List<Order> {
        return orders
    }

    override fun updateOrder(order: Order) {
        deleteOrderWithId(order.id)
        createOrder(order)
    }
}
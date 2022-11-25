package application.actions

import application.domain.Order
import application.domain.ports.provided.CreateOrder
import application.domain.ports.required.OrderRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class CreateOrderImpl : CreateOrder, KoinComponent {
    private val orderRepository: OrderRepository by inject()

    override operator fun invoke(): Order {
        val order = Order(UUID.randomUUID())
        orderRepository.createOrder(order)
        return order
    }
}
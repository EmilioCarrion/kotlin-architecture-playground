package application.actions

import application.domain.Order
import application.ports.provided.GetAllOrders
import application.ports.required.OrderRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetAllOrdersImpl : GetAllOrders, KoinComponent {
    private val orderRepository: OrderRepository by inject()

    override fun invoke(): List<Order> {
        return orderRepository.getAllOrders()
    }
}
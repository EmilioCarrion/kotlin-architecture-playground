package application.actions

import application.domain.Order
import application.domain.ports.provided.GetAllOrders
import application.domain.ports.required.OrderRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetAllOrdersImpl : GetAllOrders, KoinComponent {
    private val orderRepository: OrderRepository by inject()

    override fun invoke(): List<Order> {
        return orderRepository.getAllOrders()
    }
}
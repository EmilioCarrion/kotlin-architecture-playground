package application.actions

import application.domain.OrderLine
import application.domain.ports.provided.AddLineToOrder
import application.domain.ports.required.OrderRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class AddLineToOrderImpl : AddLineToOrder, KoinComponent {
    private val orderRepository: OrderRepository by inject()

    override fun invoke(orderId: UUID, sku: Int, quantity: Int) {
        orderRepository.getOrderById(orderId)?.let {
            it.addLine(OrderLine(sku, quantity))
            orderRepository.updateOrder(it)
        }
    }
}
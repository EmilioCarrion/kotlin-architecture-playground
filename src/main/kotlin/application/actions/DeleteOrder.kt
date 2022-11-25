package application.actions

import application.domain.ports.provided.DeleteOrder
import application.domain.ports.required.OrderRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class DeleteOrderImpl : DeleteOrder, KoinComponent {
    private val orderRepository: OrderRepository by inject()

    override fun invoke(id: UUID) {
        orderRepository.deleteOrderWithId(id)
    }
}
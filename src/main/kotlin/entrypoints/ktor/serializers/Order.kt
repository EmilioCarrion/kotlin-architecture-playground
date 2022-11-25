package entrypoints.ktor.serializers

import application.domain.Order
import application.domain.OrderLine
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class OrderSerializer(val id: String, val lines: List<OrderLineSerializer>) {
    companion object {
        fun fromDomain(order: Order): OrderSerializer {
            return OrderSerializer(
                order.id.toString(),
                order.lines.map { OrderLineSerializer.fromDomain(it) }
            )
        }
    }
}


@Serializable
data class OrderLineSerializer(val sku: Int, val quantity: Int) {
    companion object {
        fun fromDomain(line: OrderLine): OrderLineSerializer {
            return OrderLineSerializer(line.sku, line.quantity)
        }
    }
}
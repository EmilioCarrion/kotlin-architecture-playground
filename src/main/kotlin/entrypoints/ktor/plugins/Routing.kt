package entrypoints.ktor.plugins

import application.domain.ports.provided.AddLineToOrder
import application.domain.ports.provided.CreateOrder
import application.domain.ports.provided.GetAllOrders
import application.ports.provided.*
import entrypoints.ktor.serializers.OrderLineSerializer
import entrypoints.ktor.serializers.OrderSerializer
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import java.util.*

fun Application.configureRouting() {
    val createOrder by inject<CreateOrder>()
    val getAllOrders by inject<GetAllOrders>()
    val addLineToOrder by inject<AddLineToOrder>()

    routing {
        route("/orders") {
            post {
                val order = createOrder()
                val orderSerializer = OrderSerializer.fromDomain(order)
                call.respondText(Json.encodeToString(orderSerializer))
            }
            get {
                val orders = getAllOrders()
                val ordersSerializer = orders.map { OrderSerializer.fromDomain(it) }
                call.respondText(Json.encodeToString(ordersSerializer))
            }
            route("/{uuid}/lines") {
                post {
                    val line = Json.decodeFromString<OrderLineSerializer>(call.receiveText())
                    addLineToOrder(
                        UUID.fromString(call.parameters["uuid"]),
                        line.sku,
                        line.quantity
                    )
                    call.respondText("DONE")
                }
            }
        }
    }
    routing {
    }
}
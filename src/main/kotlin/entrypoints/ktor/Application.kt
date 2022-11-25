package entrypoints.ktor

import adapters.InMemoryOrderRepository
import application.actions.*
import application.ports.provided.*


import entrypoints.ktor.plugins.configureRouting

import application.ports.required.OrderRepository
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.koin.dsl.module


fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::applicationModule).start(wait = true)
}

fun Application.applicationModule() {
    val appModule = module {
        // Repos
        single<OrderRepository> { InMemoryOrderRepository() }

        // Actions
        single<CreateOrder> { CreateOrderImpl() }
        single<DeleteOrder> { DeleteOrderImpl() }
        single<AddLineToOrder> { AddLineToOrderImpl() }
        single<GetAllOrders> { GetAllOrdersImpl() }
    }

    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }

    configureRouting()
}
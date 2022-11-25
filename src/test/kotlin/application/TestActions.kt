package application

import adapters.InMemoryOrderRepository
import application.actions.GetAllOrdersImpl
import application.domain.Order
import application.domain.ports.provided.GetAllOrders
import application.domain.ports.required.OrderRepository
import entrypoints.ktor.BaseApplicationTest
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.mockk.every
import io.mockk.mockk
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals


class ActionTests {
    @Test
    fun testModule1() {
        val order = Order(UUID.randomUUID())

        val ordersRepo = InMemoryOrderRepository()
        ordersRepo.createOrder(order)

        startKoin {
            modules(
                module {
                    single<OrderRepository> { ordersRepo }
                }
            )
        }

        val getAllOrders = GetAllOrdersImpl()
        val orders = getAllOrders()
        assertEquals(orders, listOf(order))
    }
}
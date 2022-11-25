package entrypoints.ktor

import application.domain.Order
import application.domain.ports.provided.GetAllOrders
import entrypoints.ktor.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import io.mockk.every
import io.mockk.mockk
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import java.util.UUID
import kotlin.test.*


open class BaseApplicationTest {
    var koinModules: Module? = null

    fun baseTestApplication(
        block: suspend ApplicationTestBuilder.() -> Unit
    ) {
        testApplication {
            application {
                koinModules?.let {
                    install(Koin) {
                        modules(it)
                    }
                }

                configureRouting()
            }

            block()
        }
    }
}


class ApplicationTest : BaseApplicationTest() {
    val getAllOrders: GetAllOrders = mockk()

    @BeforeTest
    fun setup() {
        koinModules = module {
            single { getAllOrders }
        }
    }

    @Test
    fun testModule1() = baseTestApplication {
        val order = Order(UUID.randomUUID())
        every { getAllOrders() } returns listOf(order)

        val response = client.get("/orders")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("[{\"id\":\"${order.id.toString()}\",\"lines\":[]}]", response.bodyAsText())
    }
}
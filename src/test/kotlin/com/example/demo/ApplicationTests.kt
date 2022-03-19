package com.example.demo

import com.airwallex.grpc.annotations.GrpcClient
import com.github.michaelbull.result.get
import demo.UserServiceRpc
import java.util.UUID
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    "grpc.server.name=test",
    "grpc.server.port=-1", // -1 for in-process server
    "grpc.client.channels.userClient.in-process=true",
    "grpc.client.channels.userClient.server-name=test"
)
class ApplicationTests {

    @Autowired
    @GrpcClient(id = "userClient")
    private lateinit var userClient: UserServiceRpc

    @Test
    fun test() = runBlocking {
        val user = userClient.get(UUID.randomUUID()).get()

        assertNotNull(user)
        assertTrue(user!!.userType is UserType.Employee)

        val employee = user.userType as UserType.Employee
        assertEquals("Jeff", employee.name)
    }
}

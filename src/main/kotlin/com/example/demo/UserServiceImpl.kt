package com.example.demo

import com.airwallex.grpc.error.Error
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import demo.UserServiceRpc
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserServiceRpc {

    override suspend fun get(request: UUID): Result<User, Error> {
        return Ok(User(request, UserType.Employee("Jeff")))
    }
}

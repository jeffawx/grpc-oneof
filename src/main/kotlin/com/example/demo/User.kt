package com.example.demo

import java.util.UUID

data class User(val id: UUID, val userType: UserType? = null)

sealed class UserType {
    class Employee(val name: String) : UserType()
    class Customer(val address: String) : UserType()
}

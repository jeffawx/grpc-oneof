package com.example.demo

import com.airwallex.mapstruct.protobuf.ProtobufConfig
import com.airwallex.type.uUID
import demo.UserKt.customer
import demo.UserKt.employee
import demo.UserOuterClass
import demo.user
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(config = ProtobufConfig::class)
interface UserTypeMapper {

    @AfterMapping
    fun fromProto(src: UserOuterClass.User, @MappingTarget target: User): User {
        val userType = when (src.userTypeCase) {
            UserOuterClass.User.UserTypeCase.EMPLOYEE -> UserType.Employee(src.employee.name)
            UserOuterClass.User.UserTypeCase.CUSTOMER -> UserType.Customer(src.customer.address)
            else -> null
        }
        return target.copy(userType = userType)
    }

    @AfterMapping
    fun toProto(src: User): UserOuterClass.User {
        return user {
            id = uUID { value = src.id.toString() }
            when (val userType = src.userType) {
                is UserType.Customer -> customer = customer { address = userType.address }
                is UserType.Employee -> employee = employee { name = userType.name }
                else -> clearUserType()
            }
        }
    }
}

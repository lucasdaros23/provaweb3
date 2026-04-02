package com.ecommerce.user.service

import com.ecommerce.user.entity.User
import com.ecommerce.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun create(user: User): User {
        return userRepository.save(user)
    }

    fun findUserById(id: String): User {
        return userRepository.findById(id.toLong())
            .orElseThrow { RuntimeException("usuario nao encontrado com o id $id") }
    }
}

package com.ecommerce.user.controller

import com.ecommerce.user.entity.User
import com.ecommerce.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun criarUsuario(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user))
    }

    @GetMapping("/{id}")
    fun obterUsuarioPorId(@PathVariable id: String): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findUserById(id))
    }
}

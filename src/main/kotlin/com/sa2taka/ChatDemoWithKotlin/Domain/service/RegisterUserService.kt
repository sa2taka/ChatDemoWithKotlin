package com.sa2taka.ChatDemoWithKotlin.Domain.service

import com.sa2taka.ChatDemoWithKotlin.Domain.model.User
import com.sa2taka.ChatDemoWithKotlin.Domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterUserService {
  @Autowired
  private lateinit var userRepository: UserRepository
  private val encoder = BCryptPasswordEncoder()

  fun registerUser(username: String, password: String ) {
    var user = User()
    user.id = username
    user.password = encoder.encode(password)
    userRepository.save(user)
  }
}
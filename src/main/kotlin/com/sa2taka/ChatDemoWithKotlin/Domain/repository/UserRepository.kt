package com.sa2taka.ChatDemoWithKotlin.Domain.repository

import com.sa2taka.ChatDemoWithKotlin.Domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

interface UserRepository: JpaRepository<User, String> {

  @RestResource(exported = false)
  fun  deleteById(aLong: String?)

}
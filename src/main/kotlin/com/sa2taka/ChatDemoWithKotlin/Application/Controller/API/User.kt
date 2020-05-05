package com.sa2taka.ChatDemoWithKotlin.Application.Controller.API

import com.sa2taka.ChatDemoWithKotlin.Domain.service.RegisterUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class User {
  @Autowired
  private lateinit var registerUserService: RegisterUserService

  @RequestMapping("/register", method = [RequestMethod.POST])
  fun registerUser(@RequestParam("username") username: String, @RequestParam("password") password: String): String {
    registerUserService.registerUser(username, password)
    return "success"
  }
}
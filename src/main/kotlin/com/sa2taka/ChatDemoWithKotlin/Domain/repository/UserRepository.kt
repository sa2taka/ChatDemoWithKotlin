package com.sa2taka.ChatDemoWithKotlin.Domain.repository

import com.sa2taka.ChatDemoWithKotlin.Domain.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
}
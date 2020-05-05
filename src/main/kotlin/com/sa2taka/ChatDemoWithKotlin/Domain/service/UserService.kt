package com.sa2taka.ChatDemoWithKotlin.Domain.service

import com.sa2taka.ChatDemoWithKotlin.Domain.model.User
import com.sa2taka.ChatDemoWithKotlin.Domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User as SpringSecurityUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService: UserDetailsService {
  @Autowired
  lateinit var userRepository: UserRepository;

  override fun loadUserByUsername(username: String?): UserDetails {
    var user: User? = null
    username?.let {
      user = userRepository.getOne(it)
    } ?: run {
      throw UsernameNotFoundException("It can not be acquired User");
    }

    user?.let {
      return generateUserForSpringSecurity(it)
    } ?: run {
      throw UsernameNotFoundException("User not found for login id: $username")
    }
  }

  fun generateUserForSpringSecurity(user: User): SpringSecurityUser {
    return SpringSecurityUser(user.id.toString(), user.password, AuthorityUtils.createAuthorityList("ROLE_USER"))
  }
}
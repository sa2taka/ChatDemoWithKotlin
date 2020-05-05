package com.sa2taka.ChatDemoWithKotlin.Domain.model


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "users")
@SuppressWarnings("serial")
class User : Serializable {
  @Id
  @Column(name = "id", nullable = false)
  var id: String = ""

  @NotNull
  @Column (name = "password", nullable = false)
  var password: String = ""
    set(value) {
      val encoder = BCryptPasswordEncoder()
      field = encoder.encode(value)
    }
}
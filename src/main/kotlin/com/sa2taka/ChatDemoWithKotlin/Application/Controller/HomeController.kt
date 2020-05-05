package com.sa2taka.ChatDemoWithKotlin.Application.Controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@Controller
class HomeController {
  @RequestMapping(path = ["/"], method = [RequestMethod.GET])
  fun index(): String {
    return "index";
  }
}
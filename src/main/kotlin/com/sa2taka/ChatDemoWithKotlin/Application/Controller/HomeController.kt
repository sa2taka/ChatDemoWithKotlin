package com.sa2taka.ChatDemoWithKotlin.Application.Controller

import org.springframework.web.bind.annotation.*

@RestController
class HomeController {
  @RequestMapping(path = ["/"], method = [RequestMethod.GET])
  fun home(@RequestParam(defaultValue = "World") name: String): String {
    return "Hello, $name, hoge"
  }
}
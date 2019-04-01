package rinchiny.kotlin.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BaseController {

    @GetMapping("/")
    fun main() = "welcome"

    @GetMapping("/oops")
    fun oops(model: Model) {
        model.addAttribute("message", "Exception")
        RuntimeException("Something bad happened")
    }
}
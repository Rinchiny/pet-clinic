package rinchiny.kotlin.controllers

import org.junit.Test
import org.junit.matchers.JUnitMatchers.containsString
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
@WebMvcTest(BaseController::class)
class BaseControllerTest {

    @Autowired lateinit var mockMvc: MockMvc

    @Test
    fun testMainPageLoading() {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(view().name("welcome"))
    }

    @Test
    fun testErrorPageLoading() {
            mockMvc.perform(get("/error"))
                    .andExpect(status().isInternalServerError)
                    .andExpect(content().string(containsString("\"status\":999,\"error\":\"None\",\"message\":\"No message available\"")))
    }
}
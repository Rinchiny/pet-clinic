package rinchiny.kotlin

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import rinchiny.kotlin.controllers.BaseController

@RunWith(SpringRunner::class)
@SpringBootTest
class SpringBootKotlinMavenPetClinicApplicationTests {

    @Autowired lateinit var baseController: BaseController

    @Test
    fun contextLoads() {

    }

}

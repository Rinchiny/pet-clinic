package rinchiny.kotlin.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import rinchiny.kotlin.dtos.VetsDTO
import rinchiny.kotlin.repos.VetRepo

@Controller
class VetController(repo: VetRepo) {

    @Autowired
    val vetRepo: VetRepo = repo

    @GetMapping("/vets.html")
    fun showListOfVets(model: MutableMap<String,Any>) : String {
        val vetsDTO = VetsDTO()
        vetsDTO.vetList.addAll(vetRepo.findAll())
        model.put("vets",vetsDTO)
        return "vets/vetList"
    }

    @GetMapping("/vets")
    @ResponseBody
    fun showVetListRecources(): VetsDTO {
        val vets = VetsDTO()
        vets.vetList.addAll(vetRepo.findAll())
        return vets
    }
}
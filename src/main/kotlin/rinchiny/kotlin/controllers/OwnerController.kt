package rinchiny.kotlin.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

import rinchiny.kotlin.entities.persons.Owner
import rinchiny.kotlin.repos.OwnerRepo
import javax.validation.Valid

const val CREATE_OR_UPDATE_OWNER_FORM: String = "owners/createOrUpdateOwnerForm"

@Controller
@RequestMapping("/owners")
class OwnerController(ownerRepo: OwnerRepo) {

    @Autowired
    private val ownerRepo: OwnerRepo = ownerRepo

    @InitBinder
    fun setAllowedFields(dataBinder: WebDataBinder) = dataBinder.setDisallowedFields("id")

    @GetMapping("/new")
    fun initCreation(model: MutableMap<String,Any>): String {
        model["owner"] = Owner()
        return CREATE_OR_UPDATE_OWNER_FORM
    }

    @PostMapping("/new")
    fun processCreation(@Valid owner: Owner, result: BindingResult): String {
        if (result.hasErrors())
            return CREATE_OR_UPDATE_OWNER_FORM
        else {
            ownerRepo.save(owner)
            return "redirect:/owners/${owner.id}"
        }
    }

    @GetMapping("/find")
    fun initSearch(model: MutableMap<String,Any>): String {
        model["owner"] = Owner()
        return "owners/findOwners"
    }

    @GetMapping
    fun processSearch(owner: Owner, result: BindingResult, model: MutableMap<String, Any>): String {

        if (owner.lastName==null) owner.lastName = ""

        val userResult: Collection<Owner> = ownerRepo.findByLastName(owner.lastName)

        if (userResult.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found")
            return "owners/findOwners"
        } else if (userResult.size==1) {
            val soleOwner = userResult.iterator().next()
            return "redirect:/owners/${soleOwner.id}"
        } else {
            model["selections"] = userResult
            return "owners/ownersList"
        }
    }

    @GetMapping("/{ownerId}/edit")
    fun initUpdate(@PathVariable("ownerId") ownerId: Int, model: Model): String {
        model.addAttribute(ownerRepo.findById(ownerId))
        return CREATE_OR_UPDATE_OWNER_FORM
    }

    @PostMapping("/{ownerId}/edit")
    fun processUpdate(@Valid owner: Owner, result: BindingResult, @PathVariable("ownerId") ownerId: Int): String {
        if (result.hasErrors())
            return CREATE_OR_UPDATE_OWNER_FORM
        else {
            owner.id = ownerId
            ownerRepo.save(owner)
            return "redirect:/owners/{ownerId}"
        }
    }

    @GetMapping("/{ownerId}")
    fun showOwner(@PathVariable("ownerId") ownerId: Int): ModelAndView {
        val mav = ModelAndView("owners/ownerDetails")
        mav.addObject(ownerRepo.findById(ownerId))
        return mav
    }
}
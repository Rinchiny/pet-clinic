package rinchiny.kotlin.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.util.StringUtils
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import rinchiny.kotlin.entities.named_entities.Pet
import rinchiny.kotlin.entities.named_entities.PetType
import rinchiny.kotlin.entities.persons.Owner
import rinchiny.kotlin.repos.OwnerRepo
import rinchiny.kotlin.repos.PetRepo
import rinchiny.kotlin.services.PetValidator
import javax.validation.Valid

const val VIEWS_PETS_CREATE_OR_UPDATE_FORM: String = "pets/createOrUpdatePetForm"

@Controller
@RequestMapping("/owners/{ownerId}")
class PetController(
        @Autowired
        val petRepo: PetRepo,

        @Autowired
        val ownerRepo: OwnerRepo
) {

    @ModelAttribute("types")
    fun populatePetTypes(): Collection<PetType> {
        return petRepo.findPetTypes()
    }

    @ModelAttribute("owner")
    fun findOwner(@PathVariable("ownerId") ownerId: Int): Owner {
        return ownerRepo.findById(ownerId)
    }

    @InitBinder("owner")
    fun initOwnerBinder(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    @InitBinder("pet")
    fun initPetBinder(dataBinder: WebDataBinder) {
        dataBinder.validator = PetValidator()
    }

    @GetMapping("/pets/new")
    fun initCreation(owner: Owner, model: ModelMap): String {
        val pet = Pet()
        owner.addPet(pet)
        model["pet"] = pet
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM
    }

    @PostMapping("/pets/new")
    fun processCreation(owner: Owner, @Valid pet: Pet, result: BindingResult, model: ModelMap): String {
        if (StringUtils.hasLength(pet.name) &&
            pet.isNew &&
            owner.getPet(pet.name, true) != null) {
            result.rejectValue("name", "duplicate", "already exists")
        }
        owner.addPet(pet)
        if (result.hasErrors()) {
            model["pet"] = pet
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM
        } else {
            petRepo.save(pet)
            return "redirect:/owners/{ownerId}"
        }
    }

    @GetMapping("/pets/{petId}/edit")
    fun initUpdateForm(@PathVariable("petId") petId: Int, model: ModelMap): String {
        val pet = petRepo.findById(petId)
        model["pet"] = pet
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM
    }

    @PostMapping("/pets/{petId}/edit")
    fun processUpdateForm(@Valid pet: Pet, result: BindingResult, owner: Owner, model: ModelMap): String {
        if (result.hasErrors()) {
            pet.owner = owner
            model["pet"] = pet
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM
        } else {
            owner.addPet(pet)
            petRepo.save(pet)
            return "redirect:/owners/{ownerId}"
        }
    }
}
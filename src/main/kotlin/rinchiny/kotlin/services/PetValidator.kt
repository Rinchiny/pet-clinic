package rinchiny.kotlin.services

import org.springframework.util.StringUtils
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import rinchiny.kotlin.entities.named_entities.Pet

class PetValidator : Validator {

    val REQUIRED = "required"

    override fun validate(obj: Any, errors: Errors) {
        val pet = Pet()
        val name = pet.name

        /* Name validation */
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", REQUIRED, REQUIRED)
        }


        /* Type validation */
        if (pet.isNew && pet.type == null) {
            errors.rejectValue("type", REQUIRED, REQUIRED)
        }


        /* Birthday validation */
        if (pet.birthDate == null) {
            errors.rejectValue("birthDate", REQUIRED, REQUIRED)
        }
    }

    override fun supports(clazz: Class<*>): Boolean {
        return Pet::class.java.isAssignableFrom(clazz)
    }
}
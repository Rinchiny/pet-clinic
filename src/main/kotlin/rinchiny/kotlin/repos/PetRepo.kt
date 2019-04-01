package rinchiny.kotlin.repos

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Transactional
import rinchiny.kotlin.entities.named_entities.Pet
import rinchiny.kotlin.entities.named_entities.PetType

interface PetRepo : Repository<Pet,Int> {

    @Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
    @Transactional(readOnly = true)
    fun findPetTypes(): List<PetType>

    @Transactional(readOnly = true)
    fun findById(id: Int): Pet

    fun save(pet: Pet)
}
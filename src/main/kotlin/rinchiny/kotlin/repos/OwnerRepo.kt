package rinchiny.kotlin.repos

import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import rinchiny.kotlin.entities.persons.Owner

interface OwnerRepo : Repository<Owner,Int> {

    @Transactional(readOnly = true)
    fun findByLastName(@Param("lastName") lastName: String): Collection<Owner>

    @Transactional(readOnly = true)
    fun findById(@Param("id") id: Int): Owner

    fun save(owner: Owner)
}
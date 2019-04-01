package rinchiny.kotlin.repos

import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Transactional
import rinchiny.kotlin.entities.persons.Vet

interface VetRepo : Repository<Vet,Int> {

    @Transactional(readOnly = true)
    @Cacheable("vets")
    fun findAll() : MutableList<Vet>
}
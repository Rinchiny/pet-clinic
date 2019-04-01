package rinchiny.kotlin.entities.persons

import org.springframework.beans.support.MutableSortDefinition
import org.springframework.beans.support.PropertyComparator
import rinchiny.kotlin.entities.base.PersonEntity
import rinchiny.kotlin.entities.named_entities.Specialty
import javax.persistence.*
import javax.persistence.JoinColumn
import javax.xml.bind.annotation.XmlElement

@Entity
@Table(name = "vets")
class Vet : PersonEntity() {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vet_specialties",
            joinColumns = [JoinColumn(name = "vet_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "specialty_id", referencedColumnName = "id")]
    )
    var specialties: Set<Specialty> = mutableSetOf()
    fun getSpecialtiesInternal(): Set<Specialty> {
        if (this.specialties==null)
            specialties = mutableSetOf()
        return this.specialties
    }

    @XmlElement
    fun getSpecialties(): List<Specialty> {
        var sortedSpecs = arrayListOf<Specialty>()
        this.specialties.forEach { it ->sortedSpecs.add(it) }
        PropertyComparator.sort(sortedSpecs,
                                MutableSortDefinition("name",
                                true,
                                true)
                                )
        return sortedSpecs
    }

    fun getNrOfSpecialties(): Int = getSpecialtiesInternal().size
}
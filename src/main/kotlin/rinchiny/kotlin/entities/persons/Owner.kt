package rinchiny.kotlin.entities.persons

import rinchiny.kotlin.entities.base.PersonEntity
import rinchiny.kotlin.entities.named_entities.Pet
import java.util.HashSet
import javax.persistence.*

@Entity
@Table(name = "owners")
class Owner() : PersonEntity() {

    @Column(name = "address")
    var address:   String = ""

    @Column(name = "city")
    var city:      String = ""

    @Column(name = "telephone")
    var telephone: String = ""

    constructor(address: String, city: String, telephone: String) : this() {
        this.address = address
        this.city = city
        this.telephone = telephone
    }

    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "owner")
    var pets: MutableSet<Pet> = mutableSetOf()

    fun addPet(pet: Pet) {
        if (pet.isNew) {
            getPetsInternal().add(pet)
        }
        pet.owner = this
    }

    fun getPetsInternal(): MutableSet<Pet> {
        if (this.pets == null) {
            this.pets = HashSet()
        }
        return this.pets
    }

    fun getPet(nam: String, ignoreNew: Boolean): Pet? {
        val name = nam.toLowerCase()
        for (pet in getPetsInternal()) {
            if (!ignoreNew || !pet.isNew) {
                val compName = pet.name.toLowerCase()
                if (compName.equals(name)) {
                    return pet
                }
            }
        }
        return null
    }
}
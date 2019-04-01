package rinchiny.kotlin.entities.base

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class PersonEntity(): BaseEntity() {

    @Column(name = "first_name")
    var firstName: String = ""

    @Column(name = "last_name")
    var lastName : String = ""

    constructor(fName: String, lName: String) : this() {
        this.firstName = fName
        this.lastName = lName
    }
}
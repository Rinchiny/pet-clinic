package rinchiny.kotlin.entities.base

import javax.persistence.Column
import javax.persistence.MappedSuperclass


//traditional close to Java approach
@MappedSuperclass
open class NamedEntity(): BaseEntity() {


    @Column(name = "name")
    var name: String = ""

    constructor(name: String) : this() {
        this.name = name
    }

    override fun toString(): String = if (this.name.equals("")) "" else this.name
}
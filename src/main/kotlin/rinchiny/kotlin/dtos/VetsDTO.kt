package rinchiny.kotlin.dtos

import rinchiny.kotlin.entities.persons.Vet
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class VetsDTO {

    @XmlElement
    var vetList: MutableList<Vet> = mutableListOf()
}
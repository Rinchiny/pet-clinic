package rinchiny.kotlin.entities.named_entities

import org.springframework.format.annotation.DateTimeFormat
import rinchiny.kotlin.entities.base.NamedEntity
import rinchiny.kotlin.entities.other_entities.Visit
import rinchiny.kotlin.entities.persons.Owner
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "pets")
class Pet() : NamedEntity() {

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var birthDate: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    @JoinColumn(name = "type_id")
    var type: PetType = PetType()

    @ManyToOne
    @JoinColumn(name = "owner_id")
    var owner: Owner = Owner()

    @OneToMany(
            cascade = [CascadeType.ALL],
            mappedBy = "petId",
            fetch = FetchType.EAGER
    )
    var visits: Set<Visit> = mutableSetOf()
}
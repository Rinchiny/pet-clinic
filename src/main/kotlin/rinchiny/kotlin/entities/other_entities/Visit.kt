package rinchiny.kotlin.entities.other_entities

import org.springframework.format.annotation.DateTimeFormat
import rinchiny.kotlin.entities.base.BaseEntity
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "visits")
class Visit() : BaseEntity() {

    @Column(name = "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var date: LocalDate = LocalDate.now()

    @Column(name = "description")
    var description: String = ""

    @Column(name = "pet_id")
    var petId: Int = 0
}
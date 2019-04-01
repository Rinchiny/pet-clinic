package rinchiny.kotlin.entities.named_entities

import rinchiny.kotlin.entities.base.NamedEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "types")
class PetType : NamedEntity()
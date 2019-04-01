package rinchiny.kotlin.entities.base

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null

        //protected constructor()

        @javax.persistence.Transient
        var isNew: Boolean = this.id == null
}
package com.app.graphql.domain.models

import lombok.Data
import javax.persistence.*

@Data
@Entity
@Table(name = "attack")
class Attack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0

    val name: String = ""
}
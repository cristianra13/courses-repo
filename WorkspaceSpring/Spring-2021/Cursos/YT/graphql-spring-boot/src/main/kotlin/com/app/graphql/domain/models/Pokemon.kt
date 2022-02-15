package com.app.graphql.domain.models

import lombok.Data
import javax.persistence.*

@Data
@Entity
@Table(name = "pokemon")
class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0

    val name: String = ""
}
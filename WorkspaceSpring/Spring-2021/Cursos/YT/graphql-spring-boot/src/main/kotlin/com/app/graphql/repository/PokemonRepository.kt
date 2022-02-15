package com.app.graphql.repository

import com.app.graphql.domain.models.Pokemon
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonRepository : JpaRepository<Pokemon, Int> {
}
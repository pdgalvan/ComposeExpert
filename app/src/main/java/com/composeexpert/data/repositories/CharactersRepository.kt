package com.composeexpert.data.repositories

import com.composeexpert.data.network.ApiClient
import com.composeexpert.data.entities.Character

object CharactersRepository : Repository<Character>() {

    suspend fun get(): List<Character> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0,100)
            .data
            .results
            .asCharacters()
    }

    suspend fun find(id: Int): Character = super.find(
        id = id,
        findActionRemote =  {
            ApiClient
                .charactersService
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )
}
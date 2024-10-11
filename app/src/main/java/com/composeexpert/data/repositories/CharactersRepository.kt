package com.composeexpert.data.repositories

import com.composeexpert.data.network.ApiClient
import com.composeexpert.data.entities.Character

object CharactersRepository {
    suspend fun getCharacters(): List<Character> {
        val response = ApiClient.charactersService.getCharacters(0,100)
        return response.data.results.asCharacters()
    }

    suspend fun finCharacter(characterId: Int) : Character {
        val response = ApiClient.charactersService.findCharacter(characterId)
        return response.data.results.first().asCharacter()
    }
}
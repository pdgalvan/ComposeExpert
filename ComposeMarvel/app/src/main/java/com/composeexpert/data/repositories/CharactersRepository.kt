package com.composeexpert.data.repositories

import com.composeexpert.data.entities.Character
import com.composeexpert.data.network.entities.Result
import com.composeexpert.data.network.remote.CharactersService
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val service: CharactersService) : Repository<Character>() {

    suspend fun get(): Result<List<Character>> = super.get {
        service
            .getCharacters(0,100)
            .data
            .results
            .asCharacters()
    }

    suspend fun find(id: Int): Result<Character> = super.find(
        id = id,
        findActionRemote =  {
            service
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )
}
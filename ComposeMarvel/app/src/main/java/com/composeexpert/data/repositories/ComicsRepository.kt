package com.composeexpert.data.repositories

import com.composeexpert.data.entities.Comic
import com.composeexpert.data.network.entities.Result
import com.composeexpert.data.network.entities.tryCall
import com.composeexpert.data.network.remote.ComicsService
import javax.inject.Inject

class ComicsRepository @Inject constructor(private val service: ComicsService) {

    suspend fun get(format: Comic.Format): Result<List<Comic>> = tryCall {
        service
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .asComics()
    }

    suspend fun find(id: Int): Result<Comic> = tryCall {
        service
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
    }
}

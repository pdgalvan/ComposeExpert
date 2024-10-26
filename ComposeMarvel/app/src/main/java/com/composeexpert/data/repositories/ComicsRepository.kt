package com.composeexpert.data.repositories

import com.composeexpert.data.entities.Comic
import com.composeexpert.data.network.ApiClient
import com.composeexpert.data.network.entities.Result
import com.composeexpert.data.network.entities.tryCall

object ComicsRepository {

    suspend fun get(format: Comic.Format): Result<List<Comic>> = tryCall {
        ApiClient
            .comicsService
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .asComics()
    }

    suspend fun find(id: Int): Result<Comic> = tryCall {
        ApiClient
            .comicsService
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
    }
}

package com.composeexpert.data.repositories

import com.composeexpert.data.entities.Comic
import com.composeexpert.data.network.ApiClient

object ComicsRepository {

    suspend fun get(format: Comic.Format): List<Comic> =
        ApiClient
            .comicsService
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .asComics()


    suspend fun find(id: Int): Comic = ApiClient
        .comicsService
        .findComic(id)
        .data
        .results
        .first()
        .asComic()

}

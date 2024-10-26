package com.composeexpert.data.repositories

import com.composeexpert.data.entities.MarvelItem
import com.composeexpert.data.network.entities.Result
import com.composeexpert.data.network.entities.tryCall

abstract class Repository<T: MarvelItem> {
    private var cache: List<T> = emptyList()

    internal suspend fun get(getAction: suspend () -> List<T>): Result<List<T>> = tryCall {
        if (cache.isEmpty()) {
            cache = getAction()
        }
        cache
    }

    internal suspend fun find(
        id: Int,
        findActionRemote: suspend () -> T,
    )  : Result<T> = tryCall {
        val item = cache.find { it.id == id }
        item ?: findActionRemote()
    }
}
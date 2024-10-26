package com.composeexpert.data.repositories

import com.composeexpert.data.entities.Event
import com.composeexpert.data.network.ApiClient
import com.composeexpert.data.network.entities.Result

object EventsRepository : Repository<Event>() {
    suspend fun get(): Result<List<Event>> = super.get {
        ApiClient
            .eventsService
            .getEvents(0, 20)
            .data
            .results
            .asEvents()
    }
    suspend fun find(id: Int): Result<Event> = super.find(
        id,
        findActionRemote = {
            ApiClient
                .eventsService
                .findEvent(id)
                .data
                .results
                .first()
                .asEvent()
        }
    )
}
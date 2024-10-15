package com.composeexpert.data.repositories

import com.composeexpert.data.entities.Event
import com.composeexpert.data.network.ApiClient

object EventsRepository : Repository<Event>() {
    suspend fun get(): List<Event> = super.get {
        ApiClient
            .eventsService
            .getEvents(0, 100)
            .data
            .results
            .asEvents()
    }
    suspend fun find(id: Int): Event = super.find(
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
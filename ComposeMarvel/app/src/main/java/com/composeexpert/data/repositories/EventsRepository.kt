package com.composeexpert.data.repositories

import com.composeexpert.data.entities.Event
import com.composeexpert.data.network.entities.Result
import com.composeexpert.data.network.remote.EventsService
import javax.inject.Inject

class EventsRepository @Inject constructor (private val service: EventsService) : Repository<Event>() {
    suspend fun get(): Result<List<Event>> = super.get {
        service
            .getEvents(0, 20)
            .data
            .results
            .asEvents()
    }
    suspend fun find(id: Int): Result<Event> = super.find(
        id,
        findActionRemote = {
            service
                .findEvent(id)
                .data
                .results
                .first()
                .asEvent()
        }
    )
}
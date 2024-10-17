package com.composeexpert.data.repositories

import com.composeexpert.data.network.entities.ApiCharacter
import com.composeexpert.data.entities.*
import com.composeexpert.data.network.entities.*

fun List<ApiCharacter>.asCharacters() = map { it.asCharacter() }

fun ApiCharacter.asCharacter(): Character = Character(
    id,
    name,
    description,
    thumbnail.asString(),
    listOf(
        comics.toDomain(ReferenceList.Type.COMIC),
        events.toDomain(ReferenceList.Type.EVENT),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls.map { Url(it.type, it.url) },
)

fun List<ApiEvent>.asEvents() = map { it.asEvent() }

fun ApiEvent.asEvent(): Event = Event(
    id,
    title,
    description,
    thumbnail.asString(),
    listOf(
        comics.toDomain(ReferenceList.Type.COMIC),
        characters.toDomain(ReferenceList.Type.CHARACTER),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls.map { Url(it.type, it.url) }
)

fun List<ApiComic>.asComics() = map { it.asComic() }

fun ApiComic.asComic(): Comic = Comic(
    id,
    title,
    description ?: "",
    thumbnail.asString(),
    listOf(
        characters.toDomain(ReferenceList.Type.CHARACTER),
        events.toDomain(ReferenceList.Type.EVENT),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls.map { Url(it.type, it.url) },
    format.toDomain(),
)

private fun String.toDomain(): Comic.Format = when (this) {
    "magazine" -> Comic.Format.MAGAZINE
    "trade paperback" -> Comic.Format.TRADE_PAPERBACK
    "hardcover" -> Comic.Format.HARDCOVER
    "digest" -> Comic.Format.DIGEST
    "graphic novel" -> Comic.Format.GRAPHIC_NOVEL
    "digital comic" -> Comic.Format.DIGITAL_COMIC
    "infinite comic" -> Comic.Format.INFINITE_COMIC
    else -> Comic.Format.COMIC
}

fun Comic.Format.toStringFormat(): String = when (this) {
    Comic.Format.COMIC -> "comic"
    Comic.Format.MAGAZINE -> "magazine"
    Comic.Format.TRADE_PAPERBACK -> "trade paperback"
    Comic.Format.HARDCOVER -> "hardcover"
    Comic.Format.DIGEST -> "digest"
    Comic.Format.GRAPHIC_NOVEL -> "graphic novel"
    Comic.Format.DIGITAL_COMIC -> "digital comic"
    Comic.Format.INFINITE_COMIC -> "infinite comic"
}

private fun ApiReferenceList.toDomain(type: ReferenceList.Type): ReferenceList {
    return ReferenceList(
        type,
        items
            ?.let { items.map { Reference(it.name) } }
            ?: emptyList()
    )
}

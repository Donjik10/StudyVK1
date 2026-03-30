package io.donjik.vkeducation.data.mapper

import io.donjik.vkeducation.data.appdetails.local.AppDetailsEntity
import io.donjik.vkeducation.domain.App

fun App.toEntity(): AppDetailsEntity {
    return AppDetailsEntity(
        id = id,
        name = name,
        developer = developer,
        category = category,
        ageRating = ageRating,
        size = size,
        iconUrl = iconUrl,
        screenshotUrlList = screenshotUrlList,
        description = description
    )
}

fun AppDetailsEntity.toDomain(): App {
    return App(
        id = id,
        name = name,
        developer = developer,
        category = category,
        ageRating = ageRating,
        size = size,
        iconUrl = iconUrl,
        screenshotUrlList = screenshotUrlList,
        description = description
    )
}
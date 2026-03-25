package io.donjik.vkeducation.data.mapper

import io.donjik.vkeducation.data.dto.AppDto
import io.donjik.vkeducation.domain.App

fun AppDto.toDomain(): App {
    return App(
        id = this.id,
        name = this.name,
        developer = this.developer,
        category = this.category,
        ageRating = this.ageRating,
        size = this.size,
        iconUrl = this.iconUrl,
        screenshotUrlList = this.screenshotUrlList,
        description = this.description
    )
}
package io.donjik.vkeducation.data.mapper

import io.donjik.vkeducation.data.dto.AppDto
import io.donjik.vkeducation.data.dto.CategoryDto
import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.domain.Category

fun AppDto.toDomain(): App {
    return App(
        name = this.name,
        developer = this.developer,
        category = when (this.category) {
            CategoryDto.APP -> Category.APP
            CategoryDto.GAME -> Category.GAME
        },
        ageRating = this.ageRating,
        size = this.size,
        iconUrl = this.iconUrl,
        screenshotUrlList = this.screenshotUrlList,
        description = this.description
    )
}
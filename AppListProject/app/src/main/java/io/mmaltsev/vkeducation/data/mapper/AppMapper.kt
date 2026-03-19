package io.mmaltsev.vkeducation.data.mapper

import io.mmaltsev.vkeducation.data.dto.AppDto
import io.mmaltsev.vkeducation.data.dto.CategoryDto
import io.mmaltsev.vkeducation.domain.App
import io.mmaltsev.vkeducation.domain.Category

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
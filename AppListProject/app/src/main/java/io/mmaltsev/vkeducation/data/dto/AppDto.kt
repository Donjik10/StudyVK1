package io.mmaltsev.vkeducation.data.dto

data class AppDto(
    val name: String,
    val developer: String,
    val category: CategoryDto,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshotUrlList: List<String>,
    val description: String
)
enum class CategoryDto {
    APP, GAME
}
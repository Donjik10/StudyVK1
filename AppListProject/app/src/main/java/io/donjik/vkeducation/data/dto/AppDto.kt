package io.donjik.vkeducation.data.dto
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
@Serializable
data class AppDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("developer") val developer: String,
    @SerialName("category") val category: CategoryDto,
    @SerialName("ageRating") val ageRating: Int = 0,
    @SerialName("size") val size: Float = 0f,
    @SerialName("iconUrl") val iconUrl: String = "",
    @SerialName("screenshotUrlList") val screenshotUrlList: List<String> = emptyList(),
    @SerialName("description") val description: String = ""
)
@Serializable
enum class CategoryDto {
    APP, GAME
}
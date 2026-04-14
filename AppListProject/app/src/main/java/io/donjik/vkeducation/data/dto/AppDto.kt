package io.donjik.vkeducation.data.dto
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class AppDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String = "",
    @SerialName("category") val category: String = "",
    @SerialName("iconUrl") val iconUrl: String = "",
    @SerialName("developer") val developer: String = "Неизвестный разработчик",
    @SerialName("ageRating") val ageRating: Int = 0,
    @SerialName("size") val size: Float = 0f,
    @SerialName("screenshotUrlList") val screenshotUrlList: List<String> = emptyList()
)

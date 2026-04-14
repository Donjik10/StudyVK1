package io.donjik.vkeducation.data.appdetails.local

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ScreenshotListConverter {

    @TypeConverter
    fun fromScreenshotList(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toScreenshotList(value: String): List<String> {
        return runCatching {
            Json.decodeFromString<List<String>>(value)
        }.getOrDefault(emptyList())
    }
}
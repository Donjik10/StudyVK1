package io.donjik.vkeducation.data.mapper

import io.donjik.vkeducation.data.dto.AppDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class AppMapperTest {

    @Test
    fun `toDomain maps id correctly`() {
        val dto = AppDto(id = "1", name = "App")
        val result = dto.toDomain()

        assertEquals("1", result.id)
    }

    @Test
    fun `toDomain maps name correctly`() {
        val dto = AppDto(id = "1", name = "VK App")
        val result = dto.toDomain()

        assertEquals("VK App", result.name)
    }

    @Test
    fun `toDomain maps list fields correctly`() {
        val dto = AppDto(
            id = "1",
            name = "VK App",
            screenshotUrlList = listOf("1.png", "2.png")
        )

        val result = dto.toDomain()

        assertEquals(listOf("1.png", "2.png"), result.screenshotUrlList)
    }

    @Test
    fun `toDomain maps description and developer correctly`() {
        val dto = AppDto(
            id = "1",
            name = "VK App",
            description = "Description",
            developer = "VK"
        )

        val result = dto.toDomain()

        assertEquals("Description", result.description)
        assertEquals("VK", result.developer)
    }

    @Test
    fun `toDomain sets wishlist false by default`() {
        val dto = AppDto(id = "1", name = "App")
        val result = dto.toDomain()

        assertFalse(result.isInWishlist)
    }
}
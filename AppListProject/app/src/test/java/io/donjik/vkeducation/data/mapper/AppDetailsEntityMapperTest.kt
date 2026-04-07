package io.donjik.vkeducation.data.mapper

import io.donjik.vkeducation.data.appdetails.local.AppDetailsEntity
import io.donjik.vkeducation.domain.App
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppDetailsEntityMapperTest {

    private val app = App(
        id = "1",
        name = "Test App",
        developer = "VK",
        category = "Education",
        ageRating = 12,
        size = 44.5f,
        iconUrl = "icon.png",
        screenshotUrlList = listOf("a.png", "b.png"),
        description = "Description",
        isInWishlist = true
    )

    @Test
    fun `toEntity maps all fields correctly`() {
        val entity = app.toEntity()

        assertEquals(app.id, entity.id)
        assertEquals(app.name, entity.name)
        assertEquals(app.developer, entity.developer)
        assertEquals(app.category, entity.category)
        assertEquals(app.ageRating, entity.ageRating)
        assertEquals(app.size, entity.size)
        assertEquals(app.iconUrl, entity.iconUrl)
        assertEquals(app.screenshotUrlList, entity.screenshotUrlList)
        assertEquals(app.description, entity.description)
        assertEquals(app.isInWishlist, entity.isInWishlist)
    }

    @Test
    fun `toDomain maps all fields correctly`() {
        val entity = AppDetailsEntity(
            id = "1",
            name = "Test App",
            developer = "VK",
            category = "Education",
            ageRating = 12,
            size = 44.5f,
            iconUrl = "icon.png",
            screenshotUrlList = listOf("a.png", "b.png"),
            description = "Description",
            isInWishlist = true
        )

        val domain = entity.toDomain()

        assertEquals(entity.id, domain.id)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.developer, domain.developer)
        assertEquals(entity.category, domain.category)
        assertEquals(entity.ageRating, domain.ageRating)
        assertEquals(entity.size, domain.size)
        assertEquals(entity.iconUrl, domain.iconUrl)
        assertEquals(entity.screenshotUrlList, domain.screenshotUrlList)
        assertEquals(entity.description, domain.description)
        assertTrue(domain.isInWishlist)
    }

    @Test
    fun `domain to entity to domain keeps data unchanged`() {
        val result = app.toEntity().toDomain()

        assertEquals(app, result)
    }
}
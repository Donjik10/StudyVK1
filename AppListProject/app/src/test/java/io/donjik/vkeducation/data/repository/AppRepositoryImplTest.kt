package io.donjik.vkeducation.data.repository

import io.donjik.vkeducation.data.appdetails.local.AppDetailsEntity
import io.donjik.vkeducation.data.dto.AppDto
import io.donjik.vkeducation.data.repository.fakes.FakeAppApi
import io.donjik.vkeducation.data.repository.fakes.FakeAppDetailsDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AppRepositoryImplTest {

    private lateinit var fakeApi: FakeAppApi
    private lateinit var fakeDao: FakeAppDetailsDao
    private lateinit var repository: AppRepositoryImpl

    @Before
    fun setup() {
        fakeApi = FakeAppApi()
        fakeDao = FakeAppDetailsDao()
        repository = AppRepositoryImpl(
            api = fakeApi,
            appDetailsDao = fakeDao
        )
    }

    @Test
    fun `getAppById returns cached app from db`() = runTest {
        fakeDao.seed(
            AppDetailsEntity(
                id = "1",
                name = "Cached App",
                developer = "VK",
                category = "Tools",
                ageRating = 12,
                size = 50f,
                iconUrl = "icon",
                screenshotUrlList = listOf("1"),
                description = "desc",
                isInWishlist = false
            )
        )

        val result = repository.getAppById("1")

        assertNotNull(result)
        assertEquals("Cached App", result?.name)
        assertEquals(0, fakeApi.detailsCallCount)
    }

    @Test
    fun `getAppById fetches from network when db is empty`() = runTest {
        fakeApi.appDetails["1"] = AppDto(
            id = "1",
            name = "Network App",
            developer = "VK",
            category = "Tools",
            ageRating = 12,
            size = 50f,
            iconUrl = "icon",
            screenshotUrlList = listOf("1"),
            description = "desc"
        )

        val result = repository.getAppById("1")

        assertNotNull(result)
        assertEquals("Network App", result?.name)
        assertEquals(1, fakeApi.detailsCallCount)
    }

    @Test
    fun `getAppById saves network app to db`() = runTest {
        fakeApi.appDetails["1"] = AppDto(
            id = "1",
            name = "Network App",
            developer = "VK",
            category = "Tools",
            ageRating = 12,
            size = 50f,
            iconUrl = "icon",
            screenshotUrlList = listOf("1"),
            description = "desc"
        )

        repository.getAppById("1")
        val saved = fakeDao.getAppDetails("1").first()

        assertNotNull(saved)
        assertEquals("Network App", saved?.name)
    }

    @Test
    fun `toggleWishlist changes false to true`() = runTest {
        fakeDao.seed(
            AppDetailsEntity(
                id = "1",
                name = "App",
                developer = "VK",
                category = "Tools",
                ageRating = 12,
                size = 50f,
                iconUrl = "icon",
                screenshotUrlList = listOf("1"),
                description = "desc",
                isInWishlist = false
            )
        )

        repository.toggleWishlist("1")
        val updated = fakeDao.getAppDetails("1").first()

        assertTrue(updated?.isInWishlist == true)
    }

    @Test
    fun `observeAppDetails emits updated wishlist value`() = runTest {
        fakeDao.seed(
            AppDetailsEntity(
                id = "1",
                name = "App",
                developer = "VK",
                category = "Tools",
                ageRating = 12,
                size = 50f,
                iconUrl = "icon",
                screenshotUrlList = listOf("1"),
                description = "desc",
                isInWishlist = false
            )
        )

        repository.toggleWishlist("1")
        val result = repository.observeAppDetails("1").first()

        assertTrue(result.isInWishlist)
    }

    @Test
    fun `getApps returns empty list on network error`() = runTest {
        fakeApi.shouldThrow = true

        val result = repository.getApps()

        assertEquals(emptyList<Any>(), result)
    }

    @Test
    fun `getAppById returns null on network error`() = runTest {
        fakeApi.shouldThrow = true

        val result = repository.getAppById("1")

        assertNull(result)
    }
}
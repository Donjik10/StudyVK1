package io.donjik.vkeducation.domain.usecase

import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.domain.usecase.fakes.FakeAppRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AppUseCasesTest {

    private lateinit var fakeRepository: FakeAppRepository

    private val app = App(
        id = "1",
        name = "Test App",
        developer = "VK",
        category = "Tools",
        ageRating = 12,
        size = 55f,
        iconUrl = "icon",
        screenshotUrlList = listOf("1"),
        description = "desc",
        isInWishlist = false
    )

    @Before
    fun setup() {
        fakeRepository = FakeAppRepository()
    }

    @Test
    fun `GetAppByIdUseCase returns app from repository`() = runTest {
        fakeRepository.appById = app
        val useCase = GetAppByIdUseCase(fakeRepository)

        val result = useCase("1")

        assertEquals(app, result)
    }

    @Test
    fun `GetAppByIdUseCase passes id to repository`() = runTest {
        val useCase = GetAppByIdUseCase(fakeRepository)

        useCase("55")

        assertEquals("55", fakeRepository.requestedId)
    }

    @Test
    fun `ObserveAppDetailsUseCase returns flow from repository`() = runTest {
        fakeRepository.observedFlow = flowOf(app)
        val useCase = ObserveAppDetailsUseCase(fakeRepository)

        val result = useCase("1").first()

        assertEquals(app, result)
    }

    @Test
    fun `ToggleWishlistUseCase calls repository toggleWishlist`() = runTest {
        val useCase = ToggleWishlistUseCase(fakeRepository)

        useCase("77")

        assertEquals("77", fakeRepository.toggledId)
    }

    @Test
    fun `GetAppsUseCase returns apps list from repository`() = runTest {
        fakeRepository.apps = listOf(app)
        val useCase = GetAppsUseCase(fakeRepository)

        val result = useCase()

        assertEquals(listOf(app), result)
    }

    @Test
    fun `GetDefaultAppUseCase returns default app from repository`() = runTest {
        fakeRepository.defaultApp = app
        val useCase = GetDefaultAppUseCase(fakeRepository)

        val result = useCase()

        assertEquals(app, result)
    }
}
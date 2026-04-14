package io.donjik.vkeducation.domain.usecase.fakes

import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.domain.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeAppRepository : AppRepository {

    var apps: List<App> = emptyList()
    var appById: App? = null
    var defaultApp: App? = null
    var observedFlow: Flow<App> = flowOf()
    var toggledId: String? = null
    var requestedId: String? = null

    override suspend fun getApps(): List<App> = apps

    override suspend fun getAppById(id: String): App? {
        requestedId = id
        return appById
    }

    override suspend fun getDefaultApp(): App {
        return defaultApp ?: throw IllegalStateException("Default app is null")
    }

    override fun observeAppDetails(id: String): Flow<App> {
        requestedId = id
        return observedFlow
    }

    override suspend fun toggleWishlist(id: String) {
        toggledId = id
    }
}
package io.donjik.vkeducation.domain

import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getApps(): List<App>
    suspend fun getAppById(id: String): App?
    suspend fun getDefaultApp(): App

    fun observeAppDetails(id: String): Flow<App>
    suspend fun toggleWishlist(id: String)
}
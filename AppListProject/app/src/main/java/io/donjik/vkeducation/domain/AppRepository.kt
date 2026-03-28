package io.donjik.vkeducation.domain

interface AppRepository {
    suspend fun getApps(): List<App>
    suspend fun getAppById(id: String): App?
    suspend fun getDefaultApp(): App
}
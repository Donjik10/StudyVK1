package io.donjik.vkeducation.domain

interface AppRepository {
    suspend fun getApps(): List<App>
    suspend fun getAppByName(name: String): App?
    suspend fun getDefaultApp(): App
}
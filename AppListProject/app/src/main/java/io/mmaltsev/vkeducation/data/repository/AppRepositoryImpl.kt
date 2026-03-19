package io.mmaltsev.vkeducation.data.repository

import io.mmaltsev.vkeducation.data.AppDataProvider
import io.mmaltsev.vkeducation.data.mapper.toDomain
import io.mmaltsev.vkeducation.domain.App
import io.mmaltsev.vkeducation.domain.AppRepository

class AppRepositoryImpl : AppRepository {

    override suspend fun getApps(): List<App> {

        return AppDataProvider.getAppsListDto().map { it.toDomain() }
    }

    override suspend fun getAppByName(name: String): App? {
        val dto = AppDataProvider.getAppsListDto().find { it.name == name }
        return dto?.toDomain()
    }

    override suspend fun getDefaultApp(): App {
        return AppDataProvider.getAppsListDto().last().toDomain()
    }
}
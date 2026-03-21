package io.donjik.vkeducation.data.repository

import io.donjik.vkeducation.data.AppDataProvider
import io.donjik.vkeducation.data.mapper.toDomain
import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(): AppRepository {

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
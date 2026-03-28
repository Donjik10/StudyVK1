package io.donjik.vkeducation.data.repository

import android.util.Log
import io.donjik.vkeducation.data.mapper.toDomain
import io.donjik.vkeducation.data.network.AppApi
import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: AppApi
) : AppRepository {

    override suspend fun getApps(): List<App> {
        return try {
            api.getCatalog().map { it.toDomain() }
        } catch (e: Exception) {
            Log.e("NetworkError", "Ошибка загрузки списка: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getAppById(id: String): App? {
        return getApps().find { it.id == id }
    }

    override suspend fun getDefaultApp(): App {
        return getApps().lastOrNull() ?: throw IllegalStateException("Список пуст")
    }
}
package io.donjik.vkeducation.data.repository

import android.util.Log
import io.donjik.vkeducation.data.appdetails.local.AppDetailsDao
import io.donjik.vkeducation.data.mapper.toDomain
import io.donjik.vkeducation.data.mapper.toEntity
import io.donjik.vkeducation.data.mapper.toDomain as dtoToDomain
import io.donjik.vkeducation.data.network.AppApi
import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.domain.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: AppApi,
    private val appDetailsDao: AppDetailsDao
) : AppRepository {

    override suspend fun getApps(): List<App> {
        return try {
            api.getCatalog().map { it.dtoToDomain() }
        } catch (e: Exception) {
            Log.e("NetworkError", "Ошибка загрузки списка: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getAppById(id: String): App? {
        Log.d("ThreadCheck", "start thread = ${Thread.currentThread().name}")

        val cachedApp = withContext(Dispatchers.IO) {
            Log.d("ThreadCheck", "db read thread = ${Thread.currentThread().name}")
            appDetailsDao.getAppDetails(id).first()
        }

        if (cachedApp != null) {
            Log.d("AppRepository", "Карточка $id загружена ИЗ БД")
            return cachedApp.toDomain()
        }

        return try {
            Log.d("AppRepository", "Карточка $id не найдена в БД, идем В СЕТЬ")
            Log.d("ThreadCheck", "before network thread = ${Thread.currentThread().name}")

            val networkApp = api.getAppDetails(id).dtoToDomain()

            withContext(Dispatchers.IO) {
                Log.d("ThreadCheck", "db insert thread = ${Thread.currentThread().name}")
                appDetailsDao.insertAppDetails(networkApp.toEntity())
                Log.d("AppRepository", "Карточка $id сохранена В БД")
            }

            networkApp
        } catch (e: Exception) {
            Log.e("AppRepository", "Ошибка загрузки карточки: ${e.message}")
            null
        }
    }

    override suspend fun getDefaultApp(): App {
        return getApps().lastOrNull()
            ?: throw IllegalStateException("Список пуст")
    }
}

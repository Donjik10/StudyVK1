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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
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
        Log.d("AppRepository", "getAppById start, id = $id")

        val cachedApp = withContext(Dispatchers.IO) {
            appDetailsDao.getAppDetails(id).first()
        }

        if (cachedApp != null) {
            Log.d("AppRepository", "Карточка $id загружена ИЗ БД")
            return cachedApp.toDomain()
        }

        return try {
            Log.d("AppRepository", "Карточка $id не найдена в БД, идем В СЕТЬ")

            val networkApp = api.getAppDetails(id).dtoToDomain()

            withContext(Dispatchers.IO) {
                appDetailsDao.insertAppDetails(networkApp.toEntity())
                Log.d("AppRepository", "Карточка $id сохранена В БД")
            }

            networkApp
        } catch (e: Exception) {
            Log.e("AppRepository", "Ошибка загрузки карточки: ${e.message}", e)
            null
        }
    }

    override suspend fun getDefaultApp(): App {
        return getApps().lastOrNull()
            ?: throw IllegalStateException("Список пуст")
    }
    override fun observeAppDetails(id: String): Flow<App> {
        return appDetailsDao.getAppDetails(id)
            .filterNotNull()
            .map { it.toDomain() }
    }

    override suspend fun toggleWishlist(id: String) {
        val currentEntity = withContext(Dispatchers.IO) {
            appDetailsDao.getAppDetails(id).first()
        }

        currentEntity?.let { entity ->
            withContext(Dispatchers.IO) {
                appDetailsDao.updateWishlistStatus(id, !entity.isInWishlist)
            }
        }
    }
}

package io.donjik.vkeducation.data.repository.fakes

import io.donjik.vkeducation.data.appdetails.local.AppDetailsDao
import io.donjik.vkeducation.data.appdetails.local.AppDetailsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAppDetailsDao : AppDetailsDao {

    private val data = mutableMapOf<String, MutableStateFlow<AppDetailsEntity?>>()

    override fun getAppDetails(id: String): Flow<AppDetailsEntity?> {
        return data.getOrPut(id) { MutableStateFlow(null) }
    }

    override suspend fun insertAppDetails(appDetails: AppDetailsEntity) {
        data.getOrPut(appDetails.id) { MutableStateFlow(null) }.value = appDetails
    }

    override suspend fun updateWishlistStatus(id: String, isInWishlist: Boolean) {
        val current = data.getOrPut(id) { MutableStateFlow(null) }.value
        if (current != null) {
            data[id]?.value = current.copy(isInWishlist = isInWishlist)
        }
    }

    fun seed(entity: AppDetailsEntity) {
        data.getOrPut(entity.id) { MutableStateFlow(null) }.value = entity
    }
}
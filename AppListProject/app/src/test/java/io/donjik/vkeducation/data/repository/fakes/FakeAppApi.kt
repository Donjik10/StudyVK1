package io.donjik.vkeducation.data.repository.fakes

import io.donjik.vkeducation.data.dto.AppDto
import io.donjik.vkeducation.data.network.AppApi

class FakeAppApi : AppApi {

    var catalog: List<AppDto> = emptyList()
    var appDetails: MutableMap<String, AppDto> = mutableMapOf()
    var shouldThrow = false
    var detailsCallCount = 0
    var catalogCallCount = 0

    override suspend fun getCatalog(): List<AppDto> {
        catalogCallCount++
        if (shouldThrow) throw RuntimeException("Network error")
        return catalog
    }

    override suspend fun getAppDetails(id: String): AppDto {
        detailsCallCount++
        if (shouldThrow) throw RuntimeException("Network error")
        return appDetails[id] ?: throw RuntimeException("Not found")
    }
}
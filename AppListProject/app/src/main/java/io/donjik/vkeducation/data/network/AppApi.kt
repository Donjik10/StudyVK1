package io.donjik.vkeducation.data.network

import io.donjik.vkeducation.data.dto.AppDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("catalog")
    suspend fun getCatalog(): List<AppDto>
    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDto
}
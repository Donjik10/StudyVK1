package io.donjik.vkeducation.domain.usecase

import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.domain.AppRepository
import javax.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<App> {
        return repository.getApps()
    }
}
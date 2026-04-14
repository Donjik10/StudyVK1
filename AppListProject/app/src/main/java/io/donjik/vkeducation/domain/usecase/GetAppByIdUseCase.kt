package io.donjik.vkeducation.domain.usecase

import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.domain.AppRepository
import javax.inject.Inject

class GetAppByIdUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: String): App? {
        return repository.getAppById(id)
    }
}
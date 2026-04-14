package io.donjik.vkeducation.domain.usecase

import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.domain.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAppDetailsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    operator fun invoke(id: String): Flow<App> {
        return repository.observeAppDetails(id)
    }
}
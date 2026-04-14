package io.donjik.vkeducation.domain.usecase

import io.donjik.vkeducation.domain.AppRepository
import javax.inject.Inject

class ToggleWishlistUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: String) {
        repository.toggleWishlist(id)
    }
}
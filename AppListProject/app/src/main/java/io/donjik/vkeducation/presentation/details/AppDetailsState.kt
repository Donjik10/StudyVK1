package io.donjik.vkeducation.presentation.details

import io.donjik.vkeducation.domain.App

data class AppDetailsState(
    val app: App? = null,
    val isLoading: Boolean = true,
    val isInWishlist: Boolean = false,
    val isError: Boolean = false
)
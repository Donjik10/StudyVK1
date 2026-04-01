package io.donjik.vkeducation.presentation.details
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.donjik.vkeducation.domain.AppRepository
import io.donjik.vkeducation.presentation.navigation.APP_ID_PARAM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val appId: String = savedStateHandle.get<String>(APP_ID_PARAM).orEmpty().also {
        Log.d("AppDetailsVM", "appId = $it") }

    private val _state = MutableStateFlow(AppDetailsState())
    val state: StateFlow<AppDetailsState> = _state.asStateFlow()

    init {
        observeAppDetails()
        viewModelScope.launch {
            loadApp()
        }
    }

    private fun observeAppDetails() {
        if (appId.isBlank()) {
            _state.value = _state.value.copy(
                isLoading = false,
                isError = true
            )
            return
        }

        viewModelScope.launch {
            repository.observeAppDetails(appId)
                .catch {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isError = true
                    )
                }
                .collect { app ->
                    _state.value = AppDetailsState(
                        app = app,
                        isLoading = false,
                        isInWishlist = app.isInWishlist,
                        isError = false
                    )
                }
        }
    }

    private suspend fun loadApp() {
        if (appId.isBlank()) {
            _state.value = _state.value.copy(
                isLoading = false,
                isError = true
            )
            return
        }

        val app = repository.getAppById(appId)
        if (app == null) {
            _state.value = _state.value.copy(
                isLoading = false,
                isError = true
            )
        }
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            repository.toggleWishlist(appId)
        }
    }
}
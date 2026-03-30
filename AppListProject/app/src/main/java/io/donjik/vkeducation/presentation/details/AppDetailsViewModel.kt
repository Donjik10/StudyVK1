package io.donjik.vkeducation.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.domain.AppRepository
import io.donjik.vkeducation.presentation.navigation.APP_ID_PARAM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val appId: String = savedStateHandle.get<String>(APP_ID_PARAM).orEmpty()

    private val _app = MutableStateFlow<App?>(null)
    val app: StateFlow<App?> = _app.asStateFlow()

    init {
        viewModelScope.launch {
            loadApp()
        }
    }

    private suspend fun loadApp() {
        if (appId.isBlank()) return
        _app.value = repository.getAppById(appId)
    }
}
package io.donjik.vkeducation.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.donjik.vkeducation.domain.App
import io.donjik.vkeducation.domain.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _app = MutableStateFlow<App?>(null)
    val app: StateFlow<App?> = _app.asStateFlow()

    fun loadApp(appName: String) {
        viewModelScope.launch {
            _app.value = repository.getAppByName(appName) ?: repository.getDefaultApp()
        }
    }
}
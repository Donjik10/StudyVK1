package io.mmaltsev.vkeducation.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.mmaltsev.vkeducation.data.repository.AppRepositoryImpl
import io.mmaltsev.vkeducation.domain.App
import io.mmaltsev.vkeducation.domain.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppDetailsViewModel(
    private val repository: AppRepository = AppRepositoryImpl()
) : ViewModel() {

    private val _app = MutableStateFlow<App?>(null)
    val app: StateFlow<App?> = _app.asStateFlow()

    fun loadApp(appName: String) {
        viewModelScope.launch {
            _app.value = repository.getAppByName(appName) ?: repository.getDefaultApp()
        }
    }
}
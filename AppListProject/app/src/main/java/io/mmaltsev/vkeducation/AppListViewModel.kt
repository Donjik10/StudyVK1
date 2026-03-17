package io.mmaltsev.vkeducation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppListViewModel : ViewModel() {

    // Состояние списка приложений
    private val _apps = MutableStateFlow<List<App>>(emptyList())
    val apps: StateFlow<List<App>> = _apps.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    private val _loadTime = MutableStateFlow("")
    val loadTime: StateFlow<String> = _loadTime.asStateFlow()

    init {
        loadApps()
        _loadTime.value = "Загружено: ${System.currentTimeMillis()}"
    }

    private fun loadApps() {
        viewModelScope.launch {
            // Используем единый источник данных
            _apps.value = AppDataProvider.getAppsList()
        }
    }

    fun onAppIconClick(appName: String) {
        _snackbarMessage.value = when (appName) {
            "RuStore" -> "Нажата иконка RuStore"
            else -> "Нажата иконка: $appName"
        }
    }

    fun onSnackbarShown() {
        _snackbarMessage.value = null
    }
}
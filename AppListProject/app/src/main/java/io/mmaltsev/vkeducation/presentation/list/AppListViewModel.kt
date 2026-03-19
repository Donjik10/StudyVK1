package io.mmaltsev.vkeducation.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.mmaltsev.vkeducation.data.repository.AppRepositoryImpl
import io.mmaltsev.vkeducation.domain.App
import io.mmaltsev.vkeducation.domain.AppRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel(
    private val repository: AppRepository = AppRepositoryImpl()
) : ViewModel() {
    private val _apps = MutableStateFlow<List<App>>(emptyList())
    val apps: StateFlow<List<App>> = _apps.asStateFlow()

    private val _snackbarChannel = Channel<String>()
    val snackbarFlow = _snackbarChannel.receiveAsFlow()

    private val _loadTime = MutableStateFlow("")
    val loadTime: StateFlow<String> = _loadTime.asStateFlow()

    init {
        loadApps()
        _loadTime.value = "Загружено: ${System.currentTimeMillis()}"
    }

    private fun loadApps() {
        viewModelScope.launch {
            _apps.value = repository.getApps()
        }
    }

    fun onAppIconClick(appName: String) {
        viewModelScope.launch {
            val message = if (appName == "RuStore") "Нажата иконка RuStore" else "Нажата иконка: $appName"
            _snackbarChannel.send(message)
        }
    }
}
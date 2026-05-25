package com.wayne.assistant.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class WayneUiState(
    val statusText: String = "Ready",
    val apiKey: String = "",
    val wakeWord: String = "hey wayne"
)

class WayneViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WayneUiState())
    val uiState: StateFlow<WayneUiState> = _uiState.asStateFlow()

    fun updateApiKey(value: String) {
        _uiState.value = _uiState.value.copy(apiKey = value)
    }

    fun saveApiKey() {
        _uiState.value = _uiState.value.copy(statusText = "API key saved")
    }

    fun updateWakeWord(value: String) {
        _uiState.value = _uiState.value.copy(wakeWord = value)
    }

    fun saveWakeWord() {
        _uiState.value = _uiState.value.copy(statusText = "Wake word saved")
    }
}

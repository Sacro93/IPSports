package com.example.ipsports.ViewModel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipsports.data.model.CenterWithSports
import com.example.ipsports.data.repository.CenterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CenterViewModel @Inject constructor(
    private val centerRepository: CenterRepository
) : ViewModel() {

    private val _centersWithSports = MutableStateFlow<List<CenterWithSports>>(emptyList())
    val centersWithSports: StateFlow<List<CenterWithSports>> = _centersWithSports.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadCenters()
    }

    private fun loadCenters() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                _centersWithSports.value = centerRepository.getCentersWithSports()
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar centros: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}

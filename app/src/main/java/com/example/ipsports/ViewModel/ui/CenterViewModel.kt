package com.example.ipsports.ViewModel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipsports.data.model.CenterWithSports
import com.example.ipsports.data.repository.CenterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CenterViewModel @Inject constructor(
    private val centerRepository: CenterRepository
) : ViewModel() {

    private val _centersWithSports = MutableStateFlow<List<CenterWithSports>>(emptyList())
    val centersWithSports: StateFlow<List<CenterWithSports>> = _centersWithSports

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadCenters()
    }

    private fun loadCenters() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _centersWithSports.value = centerRepository.getCentersWithSports() // 🔹 Ahora es `suspend fun`
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }
}

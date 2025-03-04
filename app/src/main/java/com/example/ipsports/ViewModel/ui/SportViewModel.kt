package com.example.ipsports.ViewModel.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipsports.data.Usecase.SaveEventUseCase
import com.example.ipsports.data.model.Sport
import com.example.ipsports.data.repository.SportRepository
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportViewModel @Inject constructor(
    private val sportRepository: SportRepository,

) : ViewModel() {

    private val _sports = MutableStateFlow<List<Sport>>(emptyList())
    val sports: StateFlow<List<Sport>> = _sports

    private val _selectedSport = MutableStateFlow<Sport?>(null)
    val selectedSport: StateFlow<Sport?> = _selectedSport

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


    init {
        loadSports()
    }

    /** Carga los deportes desde Firestore */
    fun loadSports() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _sports.value = sportRepository.getSports()
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar deportes: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /** Selecciona un deporte */
    fun selectSport(sport: Sport) {
        _selectedSport.value = sport
    }


}


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
    private val saveEventUseCase: SaveEventUseCase

) : ViewModel() {

    private val _sports = MutableStateFlow<List<Sport>>(emptyList())
    val sports: StateFlow<List<Sport>> = _sports

    private val _selectedSport = MutableStateFlow<Sport?>(null)
    val selectedSport: StateFlow<Sport?> = _selectedSport

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _eventId =
        MutableStateFlow<String?>(null) // ✅ Agregado para almacenar el ID del evento
    val eventId: StateFlow<String?> = _eventId

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

    /** Guarda un evento y almacena el ID generado */
    fun saveEvent(
        sportId: String,
        courtId: String,
        userId: String,
        invitedFriends: List<String>,
        date: Timestamp
    ) {
        viewModelScope.launch {
            val result = saveEventUseCase.execute(sportId, courtId, userId, invitedFriends, date)

            if (result.isSuccess) {
                _eventId.value = result.getOrNull() // ✅ Obtiene el ID del evento creado
            } else {
                _errorMessage.value =
                    "Error al guardar evento: ${result.exceptionOrNull()?.localizedMessage}"
            }
        }
    }
}


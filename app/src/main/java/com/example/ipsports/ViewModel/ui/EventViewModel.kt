package com.example.ipsports.ViewModel.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipsports.data.model.Event
import com.example.ipsports.data.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    private val _eventId = MutableStateFlow<String?>(null) // ✅ Estado para el ID del evento creado
    val eventId: StateFlow<String?> = _eventId

    private val _eventsList = MutableStateFlow<List<Event>>(emptyList()) // ✅ Nueva lista de eventos
    val eventsList: StateFlow<List<Event>> = _eventsList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    /** Agrega un evento a Firestore y almacena el ID generado */
    fun addEvento(event: Event) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = eventRepository.addEvent(
                    sportId = event.sportId,
                    courtId = event.courtId,
                    userId = event.userId,
                    invitedFriends = event.usersInvited,
                    date = event.date
                )

                if (result.isSuccess) {
                    _eventId.value = result.getOrNull() // ✅ Guarda el ID del evento creado
                } else {
                    _errorMessage.value = "Error al guardar evento: ${result.exceptionOrNull()?.localizedMessage}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error inesperado: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /** Obtiene los eventos de un usuario desde Firestore */
    fun loadEventsByUser(userId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val events = eventRepository.getEventsByUser(userId)
                _eventsList.value = events // ✅ Ahora se guarda correctamente en `_eventsList`
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar eventos: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

package com.example.ipsports.ViewModel.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipsports.data.model.Event
import com.example.ipsports.data.repository.EventRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    private val _eventId = MutableStateFlow<String?>(null)
    val eventId: StateFlow<String?> = _eventId

    private val _eventsList = MutableStateFlow<List<Event>>(emptyList())
    val eventsList: StateFlow<List<Event>> = _eventsList

    private val _latestEvent = MutableStateFlow<Event?>(null) // 🔹 Estado para el evento más reciente
    val latestEvent: StateFlow<Event?> = _latestEvent.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadLatestEvent() // ✅ Cargar el evento más reciente al iniciar
    }

    /**  Agrega un evento a Firestore y recarga el más reciente */
    fun addEvento(event: Event, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = eventRepository.addEvent(event)

                if (result.isSuccess) {
                    _eventId.value = result.getOrNull()
                    loadLatestEvent()
                    onSuccess()
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

    /** 🔹 Obtiene el evento más reciente del usuario */
    fun loadLatestEvent() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
                val latest = eventRepository.getLatestEvent(userId)
                _latestEvent.value = latest
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar el evento más reciente: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /** 🔹 Obtiene la lista completa de eventos del usuario */
    fun loadEventsByUser(userId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val events = eventRepository.getEventsByUser(userId)
                _eventsList.value = events
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar eventos: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}


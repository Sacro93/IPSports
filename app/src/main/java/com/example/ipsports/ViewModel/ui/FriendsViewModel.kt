package com.example.ipsports.ViewModel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipsports.data.model.Friend
import com.example.ipsports.data.repository.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


/*
✅ availableFriends → Usuarios disponibles para agregar como amigos.
✅ friendsList → Lista de amigos ya agregados.
✅ isLoading → Indica si se está cargando la información.
✅ errorMessage → Maneja errores si ocurre un problema.
✅ loadAvailableFriends() → Obtiene la lista de usuarios disponibles.
✅ loadFriends() → Obtiene la lista de amigos ya agregados.
✅ sendFriendRequest(friendId) → Envía una solicitud de amistad a otro usuario.
*/

@HiltViewModel
class FriendViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {

    private val _availableFriends = MutableStateFlow<List<Friend>>(emptyList()) // 🔹 Usuarios disponibles para agregar
    val availableFriends: StateFlow<List<Friend>> = _availableFriends.asStateFlow()

    private val _friendsList = MutableStateFlow<List<Friend>>(emptyList()) // 🔹 Lista de amigos ya agregados
    val friendsList: StateFlow<List<Friend>> = _friendsList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
      //  loadAvailableFriends()
        loadFriends()
    }

    /** 🔹 Cargar la lista de usuarios disponibles para agregar como amigos */
    fun loadAvailableFriends() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _availableFriends.value = friendRepository.getAllUsers()
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar amigos disponibles: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /** 🔹 Cargar amigos confirmados */
    fun loadFriends() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _friendsList.value = friendRepository.getAcceptedFriends()
            } catch (e: Exception) {
                _isLoading.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }
    /** 🔹 Enviar solicitud de amistad a otro usuario */
    fun sendFriendRequest(friendId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val success = friendRepository.sendFriendRequest(friendId)
                if (!success) {
                    _errorMessage.value = "No se pudo enviar la solicitud"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al enviar solicitud: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

package com.example.ipsports.ViewModel.ui
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipsports.data.model.User
import com.example.ipsports.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage



    /** 🔹 Carga el usuario desde Firestore */
    fun loadUser(userId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = userRepository.getUser(userId)
            if (result.isSuccess) {
                _user.value = result.getOrNull()
            } else {
                _errorMessage.value = "Error al cargar usuario: ${result.exceptionOrNull()?.localizedMessage}"
            }
            _isLoading.value = false
        }
    }

    /** Guarda un nuevo usuario en Firestore */
    fun saveUser(user: User) {
        viewModelScope.launch {
            handleFirestoreOperation(
                operation = { userRepository.addUser(user) },
                onSuccess = { _user.value = user }
            )
        }
    }

    /** 🔹 Actualiza los datos del usuario en Firestore */
    fun updateUser(user: User) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = userRepository.updateUser(user)
            if (result.isFailure) {
                _errorMessage.value = "Error al actualizar usuario: ${result.exceptionOrNull()?.localizedMessage}"
            } else {
                _user.value = user // 🔹 Refresca los datos después de actualizar en Firestore
            }
            _isLoading.value = false
        }
    }

    /** Función auxiliar para manejar errores de Firestore */
    private suspend fun <T> handleFirestoreOperation(
        operation: suspend () -> Result<T>,
        onSuccess: (T) -> Unit
    ) {
        _isLoading.value = true
        try {
            val result = operation()
            if (result.isSuccess) {
                onSuccess(result.getOrThrow()) // ✅ Obtiene el valor si tuvo éxito
            } else {
                _errorMessage.value = "Error: ${result.exceptionOrNull()?.localizedMessage}"
            }
        } catch (e: Exception) {
            _errorMessage.value = "Error inesperado: ${e.localizedMessage}"
        } finally {
            _isLoading.value = false
        }
    }
}


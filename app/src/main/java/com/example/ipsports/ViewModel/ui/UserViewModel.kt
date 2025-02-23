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



    /** Obtiene el usuario desde Firestore */
    fun loadUser(userId: String) {
        viewModelScope.launch {
            handleFirestoreOperation(
                operation = { userRepository.getUser(userId) },
                onSuccess = { user -> _user.value = user }
            )
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

    /** Actualiza los datos del usuario */
    fun updateUser(user: User) {
        viewModelScope.launch {
            handleFirestoreOperation(
                operation = { userRepository.updateUser(user) },
                onSuccess = { _user.value = user }
            )
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


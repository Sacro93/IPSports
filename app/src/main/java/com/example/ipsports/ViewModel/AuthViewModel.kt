package com.example.ipsports.ViewModel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ipsports.Model.Firebase.AuthResult
import com.example.ipsports.Model.Usecase.LoginUseCase
import com.example.ipsports.Model.Usecase.RegisterUseCase

/*

solo comunica los datos a la UI
ViewModel debe verificar lo siguiente:

El email no está vacío y tiene formato válido.
La contraseña tiene al menos 8 caracteres y una mayúscula.
Las contraseñas coinciden.
El nombre, apellido y localidad no están vacíos.
*/

class AuthViewModel(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _authResult = MutableLiveData<AuthResult>()
    val authResult: LiveData<AuthResult> = _authResult

    fun registerUser(
        email: String,
        password: String,
        confirmPassword: String,
        name: String,
        surname: String,
        location: String
    ) {
        // 🔹 Estado de carga
        _authResult.value = AuthResult.Loading

        // 🔹 Validación 1: Campos no vacíos
        if (email.isBlank() || name.isBlank() || surname.isBlank() || location.isBlank()) {
            _authResult.value = AuthResult.Failure(Exception("Todos los campos son obligatorios"))
            return
        }

        // 🔹 Validación 2: Email con formato válido
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _authResult.value = AuthResult.Failure(Exception("Correo electrónico no válido"))
            return
        }

        // 🔹 Validación 3: Contraseña con al menos 8 caracteres y una mayúscula
        if (!isValidPassword(password)) {
            _authResult.value = AuthResult.Failure(Exception("La contraseña debe tener al menos 8 caracteres y una mayúscula"))
            return
        }

        // 🔹 Validación 4: Coincidencia de contraseñas
        if (password != confirmPassword) {
            _authResult.value = AuthResult.Failure(Exception("Las contraseñas no coinciden"))
            return
        }

        // 🔹 Si pasa las validaciones, intentar registrar al usuario
        registerUseCase(email, password, name, surname, location) { result ->
            _authResult.postValue(
                result.getOrNull()?.let { AuthResult.Success(it) }
                    ?: AuthResult.Failure(result.exceptionOrNull() ?: Exception("Error desconocido al registrar usuario"))
            )
        }
    }

    fun loginUser(email: String, password: String) {
        _authResult.value = AuthResult.Loading // 🔹 Estado de carga

        loginUseCase(email, password) { result ->
            _authResult.postValue(
                result.getOrNull()?.let { AuthResult.Success(it) }
                    ?: AuthResult.Failure(result.exceptionOrNull() ?: Exception("Error desconocido al iniciar sesión"))
            )
        }
    }

    // 🔹 Función para validar la contraseña (8 caracteres mínimo y al menos 1 mayúscula)
    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Z]).{8,}$")
        return passwordRegex.matches(password)
    }
}


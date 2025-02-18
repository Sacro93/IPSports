package com.example.ipsports.ViewModel.Auth

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ipsports.Model.Auth.AuthRepository
import com.example.ipsports.Model.Auth.AuthResult
import com.example.ipsports.Model.Usecase.LoginUseCase
import com.example.ipsports.Model.Usecase.RegisterUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*

solo comunica los datos a la UI
ViewModel debe verificar lo siguiente:

El email no está vacío y tiene formato válido.
La contraseña tiene al menos 8 caracteres y una mayúscula.
Las contraseñas coinciden.
El nombre, apellido y localidad no están vacíos.
*/
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authResult = MutableLiveData<AuthResult>()
    val authResult: LiveData<AuthResult> = _authResult

    private val _emailVerificationResult = MutableLiveData<Pair<Boolean, String?>>()
    val emailVerificationResult: LiveData<Pair<Boolean, String?>> = _emailVerificationResult

    fun registerUser(email: String, password: String, confirmPassword: String, name: String, surname: String, location: String) {
        _authResult.value = AuthResult.Loading

        if (email.isBlank() || name.isBlank() || surname.isBlank() || location.isBlank()) {
            _authResult.value = AuthResult.Failure(Exception("Todos los campos son obligatorios"))
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _authResult.value = AuthResult.Failure(Exception("Correo electrónico no válido"))
            return
        }

        if (!isValidPassword(password)) {
            _authResult.value = AuthResult.Failure(Exception("La contraseña debe tener al menos 8 caracteres y una mayúscula"))
            return
        }

        if (password != confirmPassword) {
            _authResult.value = AuthResult.Failure(Exception("Las contraseñas no coinciden"))
            return
        }

        registerUseCase(email, password, name, surname, location) { result ->
            _authResult.postValue(result)

            if (result is AuthResult.Success) {
                sendVerificationEmail()  // ✅ Enviar email automáticamente tras el registro
            }
        }
    }

    fun loginUser(email: String, password: String) {
        _authResult.value = AuthResult.Loading

        loginUseCase(email, password) { result ->
            when (result) {
                is AuthResult.Success -> {
                    val user = result.user
                    if (user != null && !user.isEmailVerified) {
                        _authResult.postValue(AuthResult.Failure(Exception("Debe verificar su correo antes de iniciar sesión")))
                    } else {
                        _authResult.postValue(result) // ✅ Usuario autenticado correctamente
                    }
                }

                is AuthResult.Failure -> {
                    _authResult.postValue(result) // ✅ Mantiene el error recibido
                }

                else -> Unit
            }
        }
    }

    fun sendVerificationEmail() {
        authRepository.sendVerificationEmail { success, message ->
            _emailVerificationResult.postValue(Pair(success, message))
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Z]).{8,}$")
        return passwordRegex.matches(password)
    }
}



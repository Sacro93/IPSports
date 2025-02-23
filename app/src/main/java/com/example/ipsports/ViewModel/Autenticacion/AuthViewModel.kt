package com.example.ipsports.ViewModel.Autenticacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipsports.data.Auth.AuthRepository
import com.example.ipsports.data.Auth.AuthResult
import com.example.ipsports.data.Usecase.LoginUseCase
import com.example.ipsports.data.Usecase.RegisterUseCase
import com.example.ipsports.data.Auth.ValidationUtils
import com.example.ipsports.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository // ✅ Agregamos UserRepository para verificar en Firestore
) : ViewModel() {


    private val _authResult = MutableStateFlow<AuthResult>(AuthResult.Loading)
    val authResult: StateFlow<AuthResult> = _authResult

    private val _emailVerificationResult = MutableStateFlow<Pair<Boolean, String?>>(Pair(false, null))
    val emailVerificationResult: StateFlow<Pair<Boolean, String?>> = _emailVerificationResult

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    init {
        refreshAuthState() // ✅ Validar si el usuario realmente está registrado en Firestore
    }


    fun registerUser(email: String, password: String, confirmPassword: String, name: String, surname: String, location: String) {
        println("🔹 registerUser() ejecutándose...")
        _authResult.value = AuthResult.Loading

        when {
            !ValidationUtils.areFieldsNotEmpty(email, name, surname, location) -> {
                _authResult.value = AuthResult.Failure(Exception("Todos los campos son obligatorios"))
            }
            !ValidationUtils.isValidEmail(email) -> {
                _authResult.value = AuthResult.Failure(Exception("Correo electrónico no válido"))
            }
            !ValidationUtils.isValidPassword(password) -> {
                _authResult.value = AuthResult.Failure(Exception("La contraseña debe tener al menos 8 caracteres y una mayúscula"))
            }
            !ValidationUtils.doPasswordsMatch(password, confirmPassword) -> {
                _authResult.value = AuthResult.Failure(Exception("Las contraseñas no coinciden"))
            }
            else -> {
                viewModelScope.launch {
                    val result = registerUseCase(email, password, name, surname, location)
                    _authResult.value = result
                }
            }
        }
    }


    fun loginUser(email: String, password: String) {
        _authResult.value = AuthResult.Loading

        viewModelScope.launch {
            val result = loginUseCase(email, password)
            _authResult.value = if (result is AuthResult.Success && result.user?.isEmailVerified == false) {
                AuthResult.Failure(Exception("Debe verificar su correo antes de iniciar sesión"))
            } else {
                result
            }
        }
    }

    fun sendVerificationEmail() {
        viewModelScope.launch {
            val result = authRepository.sendVerificationEmail()
            _emailVerificationResult.value = Pair(result.isSuccess, result.exceptionOrNull()?.localizedMessage)
        }
    }

    fun refreshAuthState() {
        viewModelScope.launch {
            val firebaseUser = authRepository.getCurrentUser()
            if (firebaseUser != null) {
                val userInFirestore = userRepository.getUser(firebaseUser.uid)
                _isUserLoggedIn.value = userInFirestore != null // ✅ Solo se autentica si hay datos en Firestore
            } else {
                _isUserLoggedIn.value = false
            }
        }
    }

    fun logout() {
        authRepository.logoutUser()
        _isUserLoggedIn.value = false
    }


}



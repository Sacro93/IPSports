package com.example.ipsports.ViewModel.Auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ipsports.Model.Auth.AuthRepository
import com.example.ipsports.Model.Auth.AuthResult
import com.example.ipsports.Model.Usecase.LoginUseCase
import com.example.ipsports.Model.Usecase.RegisterUseCase
import com.example.ipsports.Model.Auth.ValidationUtils
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

    fun registerUser(
        email: String,
        password: String,
        confirmPassword: String,
        name: String,
        surname: String,
        location: String
    ) {
        _authResult.value = AuthResult.Loading

        // Validaciones usando ValidationUtils
        if (!ValidationUtils.areFieldsNotEmpty(email, name, surname, location)) {
            _authResult.value = AuthResult.Failure(Exception("Todos los campos son obligatorios"))
            return
        }

        if (!ValidationUtils.isValidEmail(email)) {
            _authResult.value = AuthResult.Failure(Exception("Correo electrónico no válido"))
            return
        }

        if (!ValidationUtils.isValidPassword(password)) {
            _authResult.value = AuthResult.Failure(Exception("La contraseña debe tener al menos 8 caracteres y una mayúscula"))
            return
        }

        if (!ValidationUtils.doPasswordsMatch(password, confirmPassword)) {
            _authResult.value = AuthResult.Failure(Exception("Las contraseñas no coinciden"))
            return
        }

        registerUseCase(email, password, name, surname, location) { result ->
            _authResult.postValue(result)
        }

        // Registrar usuario en Firebase Auth
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = FirebaseAuth.getInstance().currentUser
                    if (user != null) {
                        // Guardar datos del usuario en Firestore
                        authRepository.saveUserData(
                            userId = user.uid,
                            name = name,
                            surname = surname,
                            email = email,
                            location = location
                        ) { result ->
                            _authResult.postValue(result)
                        }
                    } else {
                        _authResult.value = AuthResult.Failure(Exception("Error al obtener el usuario autenticado"))
                    }
                } else {
                    _authResult.value = AuthResult.Failure(task.exception ?: Exception("Error al registrar el usuario"))
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
            // Actualizar _emailVerificationResult con el resultado
            _emailVerificationResult.postValue(Pair(success, message))
        }
    }


}



package com.example.ipsports.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ipsports.Model.Usecase.LoginUseCase
import com.example.ipsports.Model.Usecase.RegisterUseCase

class AuthViewModelFactory(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(registerUseCase, loginUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
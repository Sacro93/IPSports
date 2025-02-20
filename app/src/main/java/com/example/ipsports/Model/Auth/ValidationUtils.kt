package com.example.ipsports.Model.Auth

import android.util.Patterns

object ValidationUtils {

    // Validar formato de correo electrónico
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Validar contraseña (mínimo 8 caracteres, al menos una mayúscula)
    fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Z]).{8,}$")
        return passwordRegex.matches(password)
    }

    // Validar que dos contraseñas coincidan
    fun doPasswordsMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    // Validar campos obligatorios
    fun areFieldsNotEmpty(vararg fields: String): Boolean {
        return fields.all { it.isNotBlank() }
    }
}
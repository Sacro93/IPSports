package com.example.ipsports.ViewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.painter.Painter

class UserViewModel : ViewModel() {
    // Imagen del usuario (null si no tiene foto)
    private val _userImage = mutableStateOf<Painter?>(null)
    val userImage: State<Painter?> get() = _userImage

    // Iniciales del usuario
    private val _userInitials = mutableStateOf("JD")
    val userInitials: State<String> get() = _userInitials

    // Método para actualizar la imagen del usuario
    fun updateUserImage(newImage: Painter) {
        _userImage.value = newImage
    }

    // Método para actualizar las iniciales del usuario
    fun updateUserInitials(newInitials: String) {
        _userInitials.value = newInitials
    }
}

package com.example.ipsports.data.Usecase

import com.example.ipsports.data.model.Event
import com.example.ipsports.data.repository.EventRepository
import javax.inject.Inject

import com.google.firebase.Timestamp

class SaveEventUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {
    suspend fun execute(event: Event): Result<String> {
        return eventRepository.addEvent(event) // ✅ Ahora pasamos el objeto completo
    }
}
package com.example.ipsports.data.Usecase

import com.example.ipsports.data.repository.EventRepository
import javax.inject.Inject

import com.google.firebase.Timestamp

class SaveEventUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {
    suspend fun execute(
        sportId: String,
        courtId: String,
        userId: String,
        invitedFriends: List<String>,
        date: Timestamp
    ): Result<String> {
        return eventRepository.addEvent(
            sportId = sportId,
            courtId = courtId,
            userId = userId,
            invitedFriends = invitedFriends,
            date = date
        )
    }
}

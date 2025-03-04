package com.example.ipsports.data.DatosDefault

import com.example.ipsports.data.model.Friend
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun populateFriends(firestore: FirebaseFirestore) {
    val usersCollection = firestore.collection("users")

    val users = listOf(
        Friend(id = "user1", name = "Juan Pérez", email = "juan@example.com"),
        Friend(id = "user2", name = "María López", email = "maria@example.com"),
        Friend(id = "user3", name = "Carlos Gómez", email = "carlos@example.com"),
        Friend(id = "user4", name = "Ana Ramírez", email = "ana@example.com")
    )

    users.forEach { friend ->
        usersCollection.document(friend.id).set(friend).await()
    }

    println("✅ Amigos ficticios agregados a Firestore")
}

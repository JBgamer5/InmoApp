package co.uts.inmoapp.data

import co.uts.inmoapp.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseDatabase {
    val firestore = FirebaseFirestore.getInstance()

    suspend fun createUser(user: Usuario, correo: String, password: String): Boolean {
        return try {
            firestore.collection(FirebaseConst.UsuariosColection.const).document(correo).set(user).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
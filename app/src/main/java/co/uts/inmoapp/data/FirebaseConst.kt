package co.uts.inmoapp.data

sealed class FirebaseConst(val const: String){
    object UsuariosColection : FirebaseConst("usuarios")
}

package com.repsol.tools.utils

import android.util.Patterns

object ValidationUtils {

    /**
     * Verifica si el email tiene un formato valido
     *
     * @param email correo electrónico a validar,
     * @return ´true´ si el correo es válido, ´false´ en caso contrario.
     */

    fun isValidEmail(email:String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
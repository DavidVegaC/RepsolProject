package com.repsol.core_domain.common.entities

enum class PhysicalCardState(val code: String, val description: String) {
    NOT_REQUESTED("03481EST01", "No Solicitado"),
    REQUESTED("03481EST02", "Solicitado"),
    GENERATED("03481EST03", "Generado"),
    PRINTED("03481EST04", "Impreso");

    companion object {

        fun fromCode(code: String): PhysicalCardState? {
            return entries.find { it.code == code }
        }
    }
}
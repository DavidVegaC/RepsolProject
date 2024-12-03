package com.repsol.core_domain.common.entities

enum class MileageStatus(val code: String, val description: String) {
    ACTIVATED("1", "Activado"),
    DEACTIVATED("0", "Desactivado");

    companion object {

        fun fromCode(code: String): MileageStatus? {
            return entries.find { it.code == code }
        }
    }
}
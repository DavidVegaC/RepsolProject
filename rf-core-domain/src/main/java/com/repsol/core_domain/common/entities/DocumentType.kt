package com.repsol.core_domain.common.entities

enum class DocumentType(val code: String, val description: String) {
    DNI("1", "DNI"),
    EXT_CARNET("4", "CARNET DE EXT."),
    RUC("6", "RUC");

    companion object {

        fun fromCode(code: String): DocumentType? {
            return entries.find { it.code == code }
        }
    }
}
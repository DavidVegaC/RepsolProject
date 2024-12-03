package com.repsol.core_domain.common.entities

enum class AttentionType(val code: String, val description: String) {
    PHYSICAL("03481EST04", "Física"),
    QR("1", "QR"),
    NON_PHYSICAL("03481EST02,03481EST03", "No Física"),
    NO_QR("0", "No QR");

    companion object {

        fun fromCode(code: String): AttentionType? {
            return entries.find { it.code.split(",").contains(code) }
        }
    }
}
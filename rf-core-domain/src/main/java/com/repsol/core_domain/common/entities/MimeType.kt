package com.repsol.core_domain.common.entities

enum class MimeType(val value: String) {
    EXCEL("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    WORD("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    PDF("application/pdf"),
    TEXT("text/plain"),
    IMAGE_PNG("image/png"),
    IMAGE_JPEG("image/jpeg");

    companion object {
        fun fromExtension(extension: String): MimeType? {
            return when (extension.lowercase()) {
                "xlsx" -> EXCEL
                "docx" -> WORD
                "pdf" -> PDF
                "txt" -> TEXT
                "png" -> IMAGE_PNG
                "jpg", "jpeg" -> IMAGE_JPEG
                else -> null
            }
        }
    }
}
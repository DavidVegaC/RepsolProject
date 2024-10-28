package com.repsol.core_domain.storage

enum class StorageScope(
    val code: String,
    val includes: List<StorageScope>
) {
    FLOW(
        code = "flow",
        includes = emptyList()
    ),
    USER(
        code = "user",
        includes = listOf(FLOW)
    ),
    APP(
        code = "app",
        includes = listOf(USER, FLOW)
    );

    companion object {

        fun firstByCode(code: String): StorageScope {
            return entries.first { it.code == code }
        }
    }
}
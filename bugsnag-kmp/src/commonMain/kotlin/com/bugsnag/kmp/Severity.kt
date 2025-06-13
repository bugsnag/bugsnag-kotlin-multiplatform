package com.bugsnag.kmp

public enum class Severity {
    ERROR,
    WARNING,
    INFO,
    ;

    public companion object {
        internal fun getSeverity(name: String): Severity {
            return when (name) {
                "info" -> INFO
                "warning" -> WARNING
                "error" -> ERROR
                else -> INFO
            }
        }

        internal fun getSeverityName(servity: Severity): String {
            return when (servity) {
                INFO -> "info"
                WARNING -> "warning"
                ERROR -> "error"
            }
        }
    }
}

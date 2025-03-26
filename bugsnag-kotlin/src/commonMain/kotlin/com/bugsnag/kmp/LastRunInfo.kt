package com.bugsnag.kmp

/**
 * Provides information about the last launch of the application, if there was one.
 */
public class LastRunInfo(

    /**
     * The number times the app has consecutively crashed during its launch period.
     */
    public val consecutiveLaunchCrashes: Long,

    /**
     * Whether the last app run ended with a crash, or was abnormally terminated by the system.
     */
    public val crashed: Boolean,

    /**
     * True if the previous app run ended with a crash during its launch period.
     */
    public val crashedDuringLaunch: Boolean,
) {
    override fun toString(): String {
        return "LastRunInfo(consecutiveLaunchCrashes=$consecutiveLaunchCrashes, crashed=$crashed, crashedDuringLaunch=$crashedDuringLaunch)"
    }
}

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLElement
import com.bugsnag.kmp.Configuration
import com.example.ExampleApps.SharedBugsnag

fun main() {
    val bugsnag = SharedBugsnag()
    val config = Configuration("YOUR-API-KEY")
    bugsnag.startBugsnag(config)

    val root = document.getElementById("root") ?: error("Root element not found")

    val title = document.createElement("h1").apply {
        textContent = "Bugsnag KMP - Kotlin/JS WebApp Example"
    }
    root.appendChild(title)

    fun createButton(text: String, onClick: () -> Unit): HTMLElement {
        val button = document.createElement("button") as HTMLElement
        button.textContent = text
        button.onclick = {
            onClick()
        }
        button.style.margin = "8px"
        button.style.padding = "8px 16px"
        button.style.fontSize = "16px"
        return button
    }

    root.appendChild(createButton("Notify Bugsnag") {
        bugsnag.manualNotify("Manual notify from Kotlin/JS WebApp")
        window.alert("Notify Bugsnag called!")
    })

    root.appendChild(createButton("Trigger Fatal Crash") {
        try {
            bugsnag.triggerFatalCrash("Fatal crash from Kotlin/JS button")
        } catch (e: Throwable) {
            window.alert("Crash triggered (caught): ${e.message}")
        }
    })

    root.appendChild(createButton("Set Metadata") {
        bugsnag.setMetadata("webapp", "sentFromJS")
        window.alert("Metadata set!")
    })

    root.appendChild(createButton("Leave Breadcrumb") {
        bugsnag.leaveBreadcrumbs("User tapped breadcrumb button")
        window.alert("Breadcrumb left!")
    })

    root.appendChild(createButton("Trigger ANR (Simulated)") {
        // JavaScript can't trigger ANR like Android
        window.alert("No ANR in JS; simulate long operation here if needed")
    })
}
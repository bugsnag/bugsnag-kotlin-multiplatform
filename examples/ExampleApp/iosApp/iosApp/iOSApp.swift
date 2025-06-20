import SwiftUI
import shared
import Bugsnag

@main
struct iOSApp: App {
    init() {
        Bugsnag.start(withApiKey: "YOUR-API-KEY")
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

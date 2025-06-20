import SwiftUI
import shared

struct ContentView: View {
    let bugsnag = SharedBugsnag()
    
    var body: some View {
        VStack(spacing: 20) {
            Text("Hello KMP")
            
            Button("Send Notification") {
                bugsnag.manualNotify(message: "iosError")
            }
                        
            Button("Trigger Fatal Crash") {
                bugsnag.triggerFatalCrash(message: "Fatal crash from iOS")
            }
                        
            Button("Set Metadata") {
                bugsnag.setMetadata(section: "ios", key: "true")
            }
                        
            Button("Leave Breadcrumb") {
                bugsnag.leaveBreadcrumbs(message: "User tapped breadcrumb button")
            }
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

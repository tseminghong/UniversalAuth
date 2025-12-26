package ax.nd.universalauth.xposed

import android.app.ActivityManager
import android.os.UserHandle
import android.os.Process
import ax.nd.xposedutil.asAccessible

object Util {
    fun getCurrentUser(): Int {
        return try {
            val m = ActivityManager::class.java.getDeclaredMethod("getCurrentUser")
            m.asAccessible().invoke(null) as Int
        } catch (e: Exception) {
            // Fallback to public API (derive from uid)
            Process.myUid() / 100000
        }
    }
}
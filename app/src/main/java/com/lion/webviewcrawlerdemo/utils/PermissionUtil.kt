
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.lion.webviewcrawlerdemo.interfaces.PermissionListener
import com.yanzhenjie.permission.AndPermission

object PermissionUtil{

    fun permissionApply(actvity: AppCompatActivity, vararg permissions: String, listener: PermissionListener) {
        AndPermission.with(actvity)
                .runtime()
                .permission(permissions)
                .onGranted{
                    listener.onGranted(permissions)
                }
                .onDenied{
                    listener.onDenied(permissions,"请打开应用权限给予对应的权限")
                }
                .start()
    }

    fun permissionApply(fragment: Fragment, vararg permissions: String, listener: PermissionListener) {
        AndPermission.with(fragment)
                .runtime()
                .permission(permissions)
                .onGranted{
                    listener.onGranted(permissions)
                }
                .onDenied{
                    listener.onDenied(permissions,"请打开应用权限给予对应的权限")
                }
                .start()
    }

    fun permissionApply(context: Context, vararg permissions: String, listener: PermissionListener) {
        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .onGranted{
                    listener.onGranted(permissions)
                }
                .onDenied{
                    listener.onDenied(permissions,"请打开应用权限给予对应的权限")
                }
                .start()
    }

}

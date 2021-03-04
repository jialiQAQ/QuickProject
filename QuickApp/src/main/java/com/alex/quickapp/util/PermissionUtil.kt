package com.alex.quickapp.util

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object PermissionUtil {
    fun checkPermission(fragment: Fragment, requestCode: Int, vararg permissions: String): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(fragment.requireContext(), permission) == PackageManager.PERMISSION_DENIED) {
                hasPermission = false
                break
            }
        }
        if (!hasPermission) {
            fragment.requestPermissions(permissions, requestCode)
        }
        return true
    }

    fun checkPermission(grantResults: IntArray): Boolean {
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false
            }
        }
        return true
    }
}
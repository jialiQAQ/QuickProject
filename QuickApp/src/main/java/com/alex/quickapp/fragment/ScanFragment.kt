package com.alex.quickapp.fragment

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.alex.quickapp.base.BaseFragment
import com.alex.quickapp.constant.RequestConstant
import com.alex.quickapp.databinding.FragmentScanBinding
import com.alex.quickapp.util.PermissionUtil

class ScanFragment : BaseFragment(), QRCodeView.Delegate {
    private lateinit var mBinding: FragmentScanBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentScanBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        if (PermissionUtil.checkPermission(this, RequestConstant.PERMISSION_CAMERA_REQUEST, Manifest.permission.CAMERA)) {
            startCamera()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (PermissionUtil.checkPermission(grantResults)) {
            startCamera()
        }
    }

    private fun startCamera() {
        mBinding.scanView.apply {
            setDelegate(this@ScanFragment)
            scanBoxView.isOnlyDecodeScanBoxArea = true
            startCamera()
            startSpotAndShowRect()
        }
    }

    override fun onPause() {
        mBinding.scanView.stopCamera()
        super.onPause()
    }

    override fun onDestroy() {
        mBinding.scanView.onDestroy()
        super.onDestroy()
    }

    override fun onScanQRCodeSuccess(result: String?) {
        Log.e("jiali", "result:$result")
        mBinding.scanView.startSpot()
    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {
        Log.e("jiali", "onCameraAmbientBrightnessChanged")
    }

    override fun onScanQRCodeOpenCameraError() {
        Log.e("jiali", "onScanQRCodeOpenCameraError")
    }
}
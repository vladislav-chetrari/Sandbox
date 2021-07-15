package io.sandbox.app.main.routes.hardware

import android.Manifest.permission.CAMERA
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AlertDialog
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat.getMainExecutor
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import io.sandbox.R
import io.sandbox.app.base.view.BaseFragment
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.fragment_camera.*


class CameraFragment : BaseFragment(R.layout.fragment_camera) {

    private lateinit var activityResultLauncher: ActivityResultLauncher<String>
    private var cameraProvider: ProcessCameraProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResultLauncher = registerForActivityResult(RequestPermission(), ::onPermissionResult)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())
    }

    override fun onStart() {
        super.onStart()
        requestPermission()
    }

    private fun requestPermission() =
        if (checkSelfPermission(requireContext(), CAMERA) == PERMISSION_GRANTED) onPermissionResult(true)
        else activityResultLauncher.launch(CAMERA)

    private fun onPermissionResult(cameraUsageGranted: Boolean) =
        if (cameraUsageGranted) setupCameraView()
        else showRequestPermissionRationale()

    private fun setupCameraView() {
        val aspectRatio = AspectRatio.RATIO_16_9
        val preview = Preview.Builder()
            .setTargetAspectRatio(aspectRatio)
            .build()
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()
            val lensFacing = if (hasBackCamera()) CameraSelector.LENS_FACING_BACK else null
            lensFacing ?: return@addListener
            cameraProvider?.unbindAll()
            val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
            cameraProvider?.bindToLifecycle(viewLifecycleOwner, cameraSelector, preview)
            preview.setSurfaceProvider(previewView.surfaceProvider)
        }, getMainExecutor(requireContext()))
    }

    private fun hasBackCamera() = cameraProvider?.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA) ?: false

    private fun showRequestPermissionRationale() {
        AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle(R.string.camera_permission_rationale_title)
            .setMessage(R.string.camera_permission_rationale_message)
            .setPositiveButton(R.string.open_app_settings_label) { _, _ -> openAppSettings() }
            .setNegativeButton(R.string.exit_label) { _, _ -> findNavController().navigateUp() }
            .show()
    }

    private fun openAppSettings() = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        .setData(Uri.fromParts("package", requireContext().packageName, null))
        .let(::startActivity)
}
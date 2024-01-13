package com.example.scancial.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.scancial.databinding.FragmentCameraBinding
import org.tensorflow.lite.examples.objectdetection.ObjectDetectorHelper
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.Rot90Op
import org.tensorflow.lite.task.vision.detector.Detection
import java.util.LinkedList
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding
    private lateinit var objectDetectorHelper: ObjectDetectorHelper
    private lateinit var bitmapBuffer: Bitmap
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var imageCapture: ImageCapture
    private lateinit var cameraProvider: ProcessCameraProvider

    private companion object {
        const val CAMERA_PERMISSION_REQUEST_CODE = 123
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (hasCameraPermission()) {
            initializeCamera()
        } else {
            requestCameraPermission()
        }
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeCamera()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Camera permission is required to use the camera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initializeCamera() {
        val context = requireContext()
        cameraExecutor = Executors.newSingleThreadExecutor()
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture,
                    createImageAnalysis(),
                )
                objectDetectorHelper = ObjectDetectorHelper(
                    context = context,
                    objectDetectorListener = object : ObjectDetectorHelper.DetectorListener {
                        override fun onError(error: String) {
                            this@CameraFragment.onError(error)
                        }

                        override fun onResults(
                            results: MutableList<Detection>?,
                            inferenceTime: Long,
                            imageHeight: Int,
                            imageWidth: Int
                        ) {
                            this@CameraFragment.onResults(results, inferenceTime, imageHeight, imageWidth)
                        }
                    }
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(context))
    }

    private fun createImageAnalysis(): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setTargetRotation(binding.viewFinder.display.rotation)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
            .build()
            .also {
                it.setAnalyzer(cameraExecutor) { image ->
                    if (!::bitmapBuffer.isInitialized) {
                        bitmapBuffer = Bitmap.createBitmap(
                            image.width,
                            image.height,
                            Bitmap.Config.ARGB_8888
                        )
                    }
                    detectObjects(image)
                }
            }
    }
    private fun rotateBitmap(bitmap: Bitmap, degrees: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degrees.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
    private fun detectObjects(image: ImageProxy) {
        image.use { imageProxy ->
            // Convert the ImageProxy to Bitmap
            val bitmap = Bitmap.createBitmap(
                imageProxy.width,
                imageProxy.height,
                Bitmap.Config.ARGB_8888
            )
            bitmap.copyPixelsFromBuffer(imageProxy.planes[0].buffer)

            // Rotate the image if needed
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            val rotatedBitmap = rotateBitmap(bitmap, rotationDegrees)

            // Process the image using ImageProcessor with NormalizationOptions
            val imageProcessor = ImageProcessor.Builder()
                .add(Rot90Op(-rotationDegrees / 90))
                .add(NormalizeOp(0f, 255f)) // Adjust normalization values based on your model requirements
                .build()

            val processedImage = imageProcessor.process(TensorImage.fromBitmap(rotatedBitmap))

            // Perform object detection on the processed image
            objectDetectorHelper.detect(processedImage.bitmap, rotationDegrees)
        }
    }



    fun onResults(
        results: MutableList<Detection>?,
        inferenceTime: Long,
        imageHeight: Int,
        imageWidth: Int
    ) {
        activity?.runOnUiThread {
            binding.overlay.setResults(
                results ?: LinkedList<Detection>(),
                imageHeight,
                imageWidth
            )
            binding.overlay.invalidate()
        }
    }

    fun onError(error: String) {
        activity?.runOnUiThread {
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
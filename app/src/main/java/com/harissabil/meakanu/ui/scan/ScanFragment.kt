package com.harissabil.meakanu.ui.scan

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.harissabil.meakanu.R
import com.harissabil.meakanu.data.local.entity.PlantEntity
import com.harissabil.meakanu.databinding.FragmentScanBinding
import com.harissabil.meakanu.helper.convertSpiner
import com.harissabil.meakanu.helper.createPartFromString
import com.harissabil.meakanu.helper.createTempFile
import com.harissabil.meakanu.helper.reduceFileImage
import com.harissabil.meakanu.helper.uriToFile
import com.harissabil.meakanu.ui.ViewModelFactory
import com.harissabil.meakanu.ui.result.ResultFragment.Companion.TYPE_HISTORY
import com.yalantis.ucrop.UCrop
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ScanFragment : Fragment() {

    private var getFile: File? = null

    private var _binding: FragmentScanBinding? = null

    private val scanViewModel by viewModels<ScanViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    requireContext(),
                    "Request Permission is not granted",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private lateinit var currentPhotoPath: String
    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)

            myFile.let { file ->
                getFile = file
//                binding.ivPreview.setImageBitmap(BitmapFactory.decodeFile(file.path))
//                binding.ivPreview.tag = "updatedTag"

                startCrop(Uri.fromFile(file))
            }
        }
    }

//    private val launcherIntentGallery = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == AppCompatActivity.RESULT_OK) {
//            val selectedImg = result.data?.data as Uri
//            selectedImg.let { uri ->
//                val myFile = uriToFile(uri, requireContext())
//                getFile = myFile
////                binding.ivPreview.setImageURI(uri)
////                binding.ivPreview.tag = "updatedTag"
//
//                startCrop(Uri.fromFile(myFile))
//            }
//        }
//    }

    // Registers a photo picker activity launcher in single-select mode.
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            val myFile = uriToFile(uri, requireContext())
            getFile = myFile
//                binding.ivPreview.setImageURI(uri)
//                binding.ivPreview.tag = "updatedTag"

            startCrop(Uri.fromFile(myFile))
        } else {
            Log.d("Media Picker", "No media selected")
        }
    }

//    @Deprecated("Deprecated in Java")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
//            val imageUri: Uri = UCrop.getOutput(data!!)!!
//            getFile = uriToFile(imageUri, requireContext())
//            binding.ivPreview.setImageURI(imageUri)
//            binding.ivPreview.tag = "updatedTag"
//        } else if (resultCode == UCrop.RESULT_ERROR) {
//            val cropError = UCrop.getError(data!!)
//            Toast.makeText(requireContext(), cropError!!.message, Toast.LENGTH_LONG).show()
//        }
//    }

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val imageUri: Uri = UCrop.getOutput(result.data!!)!!
                getFile = uriToFile(imageUri, requireContext())
                binding.ivPreview.setImageURI(imageUri)
                binding.ivPreview.tag = "updatedTag"
            } else if (result.resultCode == UCrop.RESULT_ERROR) {
                val cropError = UCrop.getError(result.data!!)
                Toast.makeText(requireContext(), cropError!!.message, Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivPreview.tag = "0"
        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                //
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        permissionLauncher.launch(Manifest.permission.CAMERA)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        scanViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        binding.apply {
            spOrgan.lifecycleOwner = this@ScanFragment
            btnCamera.setOnClickListener {
                if (allPermissionsGranted()) startCamera()
                else requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
            }
            btnGallery.setOnClickListener { startGallery() }
            btnUpload.setOnClickListener { uploadImage() }
        }
    }

    private fun startCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(requireActivity().packageManager)

        createTempFile(requireContext()).also {
            val photoUri: Uri = FileProvider.getUriForFile(
                requireContext(),
                "com.harissabil.meakanu",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            launcherIntentCamera.launch(intent)
        }
    }

    private fun startGallery() {
//        val intent = Intent()
//        intent.action = Intent.ACTION_GET_CONTENT
//        intent.type = "image/*"
//        val chooser = Intent.createChooser(intent, "Choose a Picture")
//        launcherIntentGallery.launch(chooser)
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun startCrop(uri: Uri) {
        val destinationUri = Uri.fromFile(getFile)

        val options = UCrop.Options()
        options.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.green_500))
        options.setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.green_700))
        options.setToolbarWidgetColor(ContextCompat.getColor(requireContext(), R.color.white))
        options.setActiveControlsWidgetColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.green_500
            )
        )

        UCrop.of(uri, destinationUri)
            .withOptions(options)
            .start(requireContext(), activityResultLauncher)
    }

    private fun uploadImage() {
        if (binding.ivPreview.tag == "updatedTag") {
            if (getFile != null) {
                val file = reduceFileImage(getFile as File)

                val map: MutableMap<String, RequestBody> = mutableMapOf()
                val selectedSpinner = binding.spOrgan.selectedIndex
                val organ = convertSpiner(selectedSpinner)
                map.put("organs", createPartFromString(organ))

                val requestImage = file.asRequestBody("image/jpeg".toMediaType())
                val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "images",
                    file.name,
                    requestImage
                )

                scanViewModel.identify(map, imageMultipart)
                scanViewModel.errorResponse.observe(viewLifecycleOwner) { error ->
                    if (error.message == null) {
                        val bundle = Bundle()
                        bundle.putParcelable("result", error)
                        bundle.putString("image", file.absolutePath)
                        bundle.putInt(TYPE_HISTORY, 0)

                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                        val current = LocalDateTime.now().format(formatter)

                        val plant =
                            PlantEntity(date = current, image = file.absolutePath, organ = organ)
                        scanViewModel.insert(plant)

                        binding.root.findNavController().navigate(
                            R.id.action_scanFragment_to_resultFragment,
                            bundle
                        )
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Error: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.root.findNavController().navigate(
                            R.id.scanFragment,
                            null,
                            NavOptions.Builder().setPopUpTo(R.id.scanFragment, true).build()
                        )
                    }
                }
                scanViewModel.errorMessage.observe(viewLifecycleOwner) { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    binding.root.findNavController().navigate(
                        R.id.scanFragment,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.scanFragment, true).build()
                    )
                }
            } else {
                Toast.makeText(requireContext(), "Upload plants image first!", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(requireContext(), "Upload plants image first!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}
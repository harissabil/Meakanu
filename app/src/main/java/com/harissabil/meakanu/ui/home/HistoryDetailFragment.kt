package com.harissabil.meakanu.ui.home

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import coil.load
import com.harissabil.meakanu.R
import com.harissabil.meakanu.data.local.entity.PlantEntity
import com.harissabil.meakanu.databinding.FragmentHistoryDetailBinding
import com.harissabil.meakanu.helper.createPartFromString
import com.harissabil.meakanu.helper.localiseDate
import com.harissabil.meakanu.helper.reduceFileImage
import com.harissabil.meakanu.ui.ViewModelFactory
import com.harissabil.meakanu.ui.result.ResultFragment
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class HistoryDetailFragment : Fragment() {

    private var _binding: FragmentHistoryDetailBinding? = null
    private val historyDetailViewModel by viewModels<HistoryDetailViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val bundle = this.arguments
        if (bundle != null) {
            val data = bundle.getParcelable<PlantEntity>("data")

            binding.apply {
                tvDate.text = localiseDate(data?.date!!)
                ivPlantImage.load(data.image)
                when (data.organ) {
                    "leaf" -> {
                        chipOrgan.setTextColor(requireContext().getColor(R.color.leaf))
                    }

                    "flower" -> {
                        chipOrgan.setTextColor(requireContext().getColor(R.color.flower))
                    }

                    "fruit" -> {
                        chipOrgan.setTextColor(requireContext().getColor(R.color.fruit))
                    }

                    "bark" -> {
                        chipOrgan.setTextColor(requireContext().getColor(R.color.bark))
                    }
                }
                chipOrgan.text = data.organ
                if (data.scientificNameWithoutAuthor != null) {
                    tvPlantScientificWitoutAuthor.text = data.scientificNameWithoutAuthor
                } else {
                    tvPlantScientificWitoutAuthor.text = "Undetermined species"
                    tvPlantScientificWitoutAuthor.typeface = Typeface.DEFAULT
                    tvPlantCommonName.visibility = View.GONE
                }
                tvPlantCommonName.text = if (data.commonName != null) data.commonName else ""

                ivPlantImage.setOnClickListener {
                    val mBundle = Bundle()
                    mBundle.putString("image", data.image)
                    val extras = FragmentNavigatorExtras(
                        ivPlantImage to "plantImage"
                    )
                    findNavController().navigate(
                        R.id.action_historyDetailFragment_to_yourImageFragment,
                        mBundle,
                        null,
                        extras
                    )
                }
            }

            historyDetailViewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }

            binding.btnUpload.setOnClickListener { uploadImage() }
        }
    }

    private fun uploadImage() {
        val bundle = this.arguments
        val data = bundle?.getParcelable<PlantEntity>("data")

        val mFile = File(data!!.image!!)
        val file = reduceFileImage(mFile)

        val map: MutableMap<String, RequestBody> = mutableMapOf()
        map.put("organs", createPartFromString(data.organ!!))

        val requestImage = file.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "images",
            file.name,
            requestImage
        )

        historyDetailViewModel.identify(map, imageMultipart)
        historyDetailViewModel.errorResponse.observe(viewLifecycleOwner) { error ->
            if (error.message == null) {
                val mBundle = Bundle()
                mBundle.putParcelable("result", error)
                mBundle.putParcelable("data", data)
                mBundle.putInt(ResultFragment.TYPE_HISTORY, 1)

                binding.root.findNavController().navigate(
                    R.id.action_historyDetailFragment_to_resultFragment,
                    mBundle
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
        historyDetailViewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            binding.root.findNavController().navigate(
                R.id.scanFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.scanFragment, true).build()
            )
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
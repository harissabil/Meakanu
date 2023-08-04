package com.harissabil.meakanu.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.harissabil.meakanu.R
import com.harissabil.meakanu.data.local.entity.PlantEntity
import com.harissabil.meakanu.data.remote.response.plantnet.ImageResponsePlantnet
import com.harissabil.meakanu.databinding.FragmentResultBinding
import com.harissabil.meakanu.helper.ConfirmButton
import com.harissabil.meakanu.ui.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class ResultFragment : Fragment(), ConfirmButton {

    private var _binding: FragmentResultBinding? = null

    private val resultViewModel by viewModels<ResultViewModel> {
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
        _binding = FragmentResultBinding.inflate(inflater, container, false)
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
            val result = bundle.getParcelable<ImageResponsePlantnet>("result")
            val image = bundle.getString("image")
            val data = bundle.getParcelable<PlantEntity>("data")
            val type = bundle.getInt(TYPE_HISTORY)

            if (type == HISTORY) {
                binding.apply {
                    if (data != null) {
                        ivPlantImage.load(data.image)
                    }
                    ivPlantImage.setOnClickListener {
                        val mBundle = Bundle()
                        if (data != null) {
                            mBundle.putString("image", data.image)
                        }
                        val extras = FragmentNavigatorExtras(
                            ivPlantImage to "plantImage"
                        )
                        findNavController().navigate(
                            R.id.action_resultFragment_to_yourImageFragment,
                            mBundle,
                            null,
                            extras
                        )
                    }

                    rvResult.layoutManager = LinearLayoutManager(requireContext())
                    rvResult.adapter = ResultListAdapter(result!!.results, this@ResultFragment)
                }
            } else {
                binding.apply {
                    ivPlantImage.load(image)
                    ivPlantImage.setOnClickListener {
                        val mBundle = Bundle()
                        mBundle.putString("image", image)
                        val extras = FragmentNavigatorExtras(
                            ivPlantImage to "plantImage"
                        )
                        findNavController().navigate(
                            R.id.action_resultFragment_to_yourImageFragment,
                            mBundle,
                            null,
                            extras
                        )
                    }

                    rvResult.layoutManager = LinearLayoutManager(requireContext())
                    rvResult.adapter = ResultListAdapter(result!!.results, this@ResultFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun showLoading(isLoading: Boolean) {
//        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//    }

    override fun confirm(scientificNameWithoutAuthor: String, commonName: String) {

        val bundle = this.arguments

        val data = bundle?.getParcelable<PlantEntity>("data")
        val type = bundle?.getInt(TYPE_HISTORY)
        if (type == HISTORY) {
            runBlocking(Dispatchers.IO) {
                if (data != null) {
                    resultViewModel.update(
                        PlantEntity(
                            data.id,
                            data.date,
                            data.image,
                            data.organ,
                            scientificNameWithoutAuthor,
                            commonName
                        )
                    )
                }
            }
            findNavController().popBackStack()
        } else {
            runBlocking(Dispatchers.IO)  {
                val plant = async { resultViewModel.getLastRow() }
                resultViewModel.update(
                    PlantEntity(
                        plant.await().id,
                        plant.await().date,
                        plant.await().image,
                        plant.await().organ,
                        scientificNameWithoutAuthor,
                        commonName
                    )
                )
            }
            findNavController().popBackStack()
        }
    }

    companion object {
        const val TYPE_HISTORY = "type_history"
        const val HISTORY = 1
    }
}
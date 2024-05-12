package com.harissabil.meakanu.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.ChipGroup
import com.harissabil.meakanu.R
import com.harissabil.meakanu.databinding.ChipPlantDistributionBinding
import com.harissabil.meakanu.databinding.FragmentPlantDetailBinding
import com.harissabil.meakanu.module.GlideApp
import com.harissabil.meakanu.ui.ViewModelFactory


class PlantDetailFragment : Fragment() {

    private var _binding: FragmentPlantDetailBinding? = null
    private val plantDetailViewModel by viewModels<PlantDetailViewModel> {
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
        _binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
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
            val plant = bundle.getInt("id")

            if (savedInstanceState == null) {
                plantDetailViewModel.byId(plant)
            }

            binding.btnRetry.setOnClickListener {
                plantDetailViewModel.byId(plant)
            }

            plantDetailViewModel.idResponse.observe(viewLifecycleOwner) { response ->
                binding.apply {
                    GlideApp.with(requireContext())
                        .load(response.data.imageUrl)
                        .placeholder(R.drawable.ic_launcher_foreground_splash)
                        .into(ivPlantImage)
                    tvPlantName.text = response.data.commonName
                    tvPlantScientificName.text = response.data.scientificName
                    tvPlantGenus.text = response.data.genus.name
                    tvPlantFamily.text = response.data.family.name
                    if (response.data.mainSpecies.distribution.native == null) {
                        tvPlantDistributionNativeTitle.visibility = View.GONE
                        cgPlantDistributionNative.visibility = View.GONE
                    } else {
                        addChips(
                            cgPlantDistributionNative,
                            response.data.mainSpecies.distribution.native
                        )
                    }
                    if (response.data.mainSpecies.distribution.introduced == null) {
                        tvPlantDistributionIntroducedTitle.visibility = View.GONE
                        cgPlantDistributionIntroduced.visibility = View.GONE
                    } else {
                        addChips(
                            cgPlantDistributionIntroduced,
                            response.data.mainSpecies.distribution.introduced
                        )
                    }

                    layoutPlantGrowth.apply {
                        if (response.data.mainSpecies.growth.soilNutriments != null) {
                            tvPlantDetailSoilNutriments.text =
                                response.data.mainSpecies.growth.soilNutriments.toString()
                        } else {
                            tvPlantDetailSoilNutriments.text = "-"
                        }
                        if (response.data.mainSpecies.growth.soilSalinity != null) {
                            tvPlantDetailSoilSalinity.text =
                                response.data.mainSpecies.growth.soilSalinity.toString()
                        } else {
                            tvPlantDetailSoilSalinity.text = "-"
                        }
                        if (response.data.mainSpecies.growth.soilHumidity != null) {
                            tvPlantDetailSoilHumidity.text =
                                response.data.mainSpecies.growth.soilHumidity.toString()
                        } else {
                            tvPlantDetailSoilHumidity.text = "-"
                        }
                        if (response.data.mainSpecies.growth.phMinimum != null) {
                            tvPlantDetailPhMin.text =
                                response.data.mainSpecies.growth.phMinimum.toString()
                        } else {
                            tvPlantDetailPhMin.text = "-"
                        }
                        if (response.data.mainSpecies.growth.phMaximum != null) {
                            tvPlantDetailPhMax.text =
                                response.data.mainSpecies.growth.phMaximum.toString()
                        } else {
                            tvPlantDetailPhMax.text = "-"
                        }
                        if (response.data.mainSpecies.growth.light != null) {
                            tvPlantDetailLight.text =
                                response.data.mainSpecies.growth.light.toString()
                        } else {
                            tvPlantDetailLight.text = "-"
                        }
                    }
                }
            }

            plantDetailViewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }

            plantDetailViewModel.isError.observe(viewLifecycleOwner) {
                showError(it)
            }

            plantDetailViewModel.errorResponse.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.nestedScrollView.visibility = if (isLoading) View.GONE else View.VISIBLE
        binding.llError.visibility = if (isLoading) View.GONE else View.GONE
    }

    private fun showError(isError: Boolean) {
        binding.llError.visibility = if (isError) View.VISIBLE else View.GONE
        binding.nestedScrollView.visibility = if (isError) View.GONE else View.GONE
    }

    private fun addChips(chipGroup: ChipGroup, keywords: List<String>) {


        chipGroup.removeAllViews()
        keywords.forEach {

            val chip = ChipPlantDistributionBinding.inflate(
                LayoutInflater.from(requireContext()),
                chipGroup, false
            ).root

            chip.text = it
            chipGroup.addView(chip)
        }
    }
}
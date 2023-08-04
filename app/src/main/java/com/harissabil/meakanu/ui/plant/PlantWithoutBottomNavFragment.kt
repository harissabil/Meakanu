package com.harissabil.meakanu.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harissabil.meakanu.databinding.FragmentPlantWithoutBottomNavBinding
import com.harissabil.meakanu.ui.ViewModelFactory

class PlantWithoutBottomNavFragment : Fragment() {

    private var _binding: FragmentPlantWithoutBottomNavBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val plantWithoutBottomNavViewModel by viewModels<PlantWithoutBottomNavViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPlantWithoutBottomNavBinding.inflate(inflater, container, false)

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
            val query = bundle.getString("query")
            if (query != null) {
                plantWithoutBottomNavViewModel.byQuery(query)
                binding.svSearch.setQuery(query, false)
            }
        }

        plantWithoutBottomNavViewModel.searchResponse.observe(viewLifecycleOwner) {
            binding.apply {
                if (it.meta.total == 0) {
                    tvNoPlants.visibility = View.VISIBLE
                    rvPlants.visibility = View.GONE
                } else {
                    tvNoPlants.visibility = View.GONE
                    rvPlants.visibility = View.VISIBLE
                    rvPlants.layoutManager = LinearLayoutManager(requireContext())
                    rvPlants.adapter = SearchListWithoutBottomNavAdapter(it.data)
                    rvPlants.adapter?.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                }
            }
        }

        plantWithoutBottomNavViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        plantWithoutBottomNavViewModel.isError.observe(viewLifecycleOwner) {
            showError(it)
        }

        plantWithoutBottomNavViewModel.searchText.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.svSearch.setQuery(it, false)
            }
        }

        plantWithoutBottomNavViewModel.errorResponse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        }

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                plantWithoutBottomNavViewModel.byQuery(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                plantWithoutBottomNavViewModel.searchText.value = query
                return false
            }
        })

        binding.btnRetry.setOnClickListener { plantWithoutBottomNavViewModel.byQuery(binding.svSearch.query.toString()) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.rvPlants.visibility = if (isLoading) View.GONE else View.VISIBLE
        binding.tvNoPlants.visibility = if (isLoading) View.GONE else View.GONE
        binding.llError.visibility = if (isLoading) View.GONE else View.GONE
    }

    private fun showError(isError: Boolean) {
        binding.llError.visibility = if (isError) View.VISIBLE else View.GONE
        binding.rvPlants.visibility = if (isError) View.GONE else View.GONE
        binding.tvNoPlants.visibility = if (isError) View.GONE else View.GONE
    }
}
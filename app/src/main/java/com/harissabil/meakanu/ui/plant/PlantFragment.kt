package com.harissabil.meakanu.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harissabil.meakanu.R
import com.harissabil.meakanu.databinding.FragmentPlantBinding
import com.harissabil.meakanu.ui.ViewModelFactory

class PlantFragment : Fragment() {

    private var _binding: FragmentPlantBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val plantViewModel by viewModels<PlantViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPlantBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_option_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_about -> {
                        findNavController().navigate(
                            R.id.action_navigation_plant_to_aboutFragment
                        )
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

//        val bundle = this.arguments
//        if (bundle != null) {
//            val query = bundle.getString("query")
//            if (query != null) {
//                plantViewModel.byQuery(query)
//                binding.svSearch.setQuery(query, false)
//            }
//        }

        plantViewModel.searchResponse.observe(viewLifecycleOwner) {
            binding.apply {
                if (it.meta.total == 0) {
                    tvNoPlants.visibility = View.VISIBLE
                    rvPlants.visibility = View.GONE
                } else {
                    tvNoPlants.visibility = View.GONE
                    rvPlants.visibility = View.VISIBLE
                    rvPlants.layoutManager = LinearLayoutManager(requireContext())
                    rvPlants.adapter = SearchListAdater(it.data)
                    rvPlants.adapter?.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                }
            }
        }

        plantViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        plantViewModel.isError.observe(viewLifecycleOwner) {
            showError(it)
        }

        plantViewModel.searchText.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.svSearch.setQuery(it, false)
            }
        }

        plantViewModel.errorResponse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        }

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                plantViewModel.byQuery(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                plantViewModel.searchText.value = query
                return false
            }
        })

        binding.btnRetry.setOnClickListener {
            if (binding.svSearch.query.isNotBlank()) {
                plantViewModel.byQuery(binding.svSearch.query.toString())
            } else {
                plantViewModel.byQuery("carrot")
            }
        }
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
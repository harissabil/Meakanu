package com.harissabil.meakanu.ui.home

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.harissabil.meakanu.R
import com.harissabil.meakanu.data.local.entity.PlantEntity
import com.harissabil.meakanu.databinding.FragmentHomeBinding
import com.harissabil.meakanu.helper.MoreClicked
import com.harissabil.meakanu.helper.deleteImage
import com.harissabil.meakanu.ui.ViewModelFactory


class HomeFragment : Fragment(), MoreClicked {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    private lateinit var historyListAdapter: HistoryListAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)

        historyListAdapter = HistoryListAdapter(this)

        binding.rvHistory.setHasFixedSize(false)
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistory.isNestedScrollingEnabled = false
        binding.rvHistory.adapter = historyListAdapter
        binding.rvHistory.adapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_option_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_about -> {
                        findNavController().navigate(
                            R.id.action_navigation_home_to_aboutFragment
                        )
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.cvBanner.setOnClickListener {
            homeViewModel.clickBanner()
        }

        homeViewModel.getLatest().observe(viewLifecycleOwner) { history ->
            if (!history.isNullOrEmpty()) {
                historyListAdapter.submitList(history)
                binding.rvHistory.visibility = View.VISIBLE
                binding.tvNoHistory.visibility = View.GONE
            } else {
                binding.tvNoHistory.visibility = View.VISIBLE
                binding.rvHistory.visibility = View.GONE
            }
        }

        homeViewModel.count.observe(viewLifecycleOwner) {
            if (it == 10) {
                Toast.makeText(
                    requireContext(),
                    "Beautiful isn't it?",
                    Toast.LENGTH_SHORT
                ).show()
                homeViewModel.resetCount()
            }
        }

        binding.cvScan.setOnClickListener {
            binding.cvScan.findNavController().navigate(R.id.action_navigation_home_to_scanFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun popUpMenu(view: View, plant: PlantEntity) {
        val popUpMenu = PopupMenu(requireContext(), view, Gravity.END)
        popUpMenu.inflate(R.menu.menu_pop_up_list)
        popUpMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete -> {
                    deleteDialog(plant)
                    true
                }

                else -> {
                    false
                }
            }
        }
        popUpMenu.show()
    }

    private fun deleteDialog(plant: PlantEntity) {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Delete observation?")
        builder.setMessage("Observation will be permanently removed from your device.")
        builder.setPositiveButton("Delete") { _, _ ->
            if (!plant.image.isNullOrBlank()) {
                deleteImage(plant.image!!)
            homeViewModel.delete(plant)
            }
            Snackbar.make(binding.root, "Observation deleted", Snackbar.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancel") { _, _ ->
        }
        builder.show()
    }
}
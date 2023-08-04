package com.harissabil.meakanu.ui.agri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harissabil.meakanu.R
import com.harissabil.meakanu.databinding.FragmentAgriBinding

class AgriFragment : Fragment() {

    private var _binding: FragmentAgriBinding? = null

    private val list = ArrayList<AgriInfo>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val agriViewModel =
            ViewModelProvider(this)[AgriViewModel::class.java]

        _binding = FragmentAgriBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)

        list.addAll(getListAgriInfo())

        binding.apply {
            rvAgri.setHasFixedSize(true)
            rvAgri.layoutManager = LinearLayoutManager(requireContext())
            rvAgri.adapter = ListAgriAdapter(list)
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_option_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_about -> {
                        findNavController().navigate(
                            R.id.action_navigation_agri_to_aboutFragment
                        )
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun getListAgriInfo(): ArrayList<AgriInfo> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataSource = resources.getStringArray(R.array.data_source)
        val dataLink = resources.getStringArray(R.array.data_link)
        val listAgriInfo = ArrayList<AgriInfo>()
        for (i in dataTitle.indices) {
            val agriInfo = AgriInfo(
                dataTitle[i],
                dataSource[i],
                dataLink[i]
            )
            listAgriInfo.add(agriInfo)
        }
        return listAgriInfo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
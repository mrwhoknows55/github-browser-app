package com.mrwhoknows.gh_browser.ui.add_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.mrwhoknows.gh_browser.R
import com.mrwhoknows.gh_browser.databinding.FragmentAddRepoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRepoFragment : Fragment() {
    private lateinit var binding: FragmentAddRepoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddRepoBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setupToolbar()
        return binding.root
    }

    private fun setupToolbar() {
        val toolbar = requireActivity().findViewById<MaterialToolbar>(R.id.mainToolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.title = findNavController().currentDestination?.label
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

}
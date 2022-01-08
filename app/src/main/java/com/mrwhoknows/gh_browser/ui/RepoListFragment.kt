package com.mrwhoknows.gh_browser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrwhoknows.gh_browser.R
import com.mrwhoknows.gh_browser.databinding.FragmentRepoListBinding

class RepoListFragment : Fragment() {

    private lateinit var binding: FragmentRepoListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoListBinding.inflate(inflater, container, false)
        return binding.root
    }

}
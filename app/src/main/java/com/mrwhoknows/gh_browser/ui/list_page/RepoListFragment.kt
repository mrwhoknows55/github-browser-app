package com.mrwhoknows.gh_browser.ui.list_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mrwhoknows.gh_browser.databinding.FragmentRepoListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class RepoListFragment : Fragment() {
    private lateinit var binding: FragmentRepoListBinding
    private val repositoryViewModel: RepositoryListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repositoryViewModel.getAllRepositories()
        repositoryViewModel.repositories.observe(viewLifecycleOwner) { list ->
            Timber.d("onViewCreated: list: $list")
        }

    }
}
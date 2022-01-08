package com.mrwhoknows.gh_browser.ui.list_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mrwhoknows.gh_browser.databinding.FragmentRepoListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class RepoListFragment : Fragment() {

    private lateinit var binding: FragmentRepoListBinding
    private val repositoryViewModel: RepositoryListViewModel by viewModels()
    private lateinit var repoAdapter: RepoListAdapter

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
        initClickListeners()
        initRV()
        repositoryViewModel.getAllRepositories()
        repositoryViewModel.repositories.observe(viewLifecycleOwner) { list ->
            Timber.d("onViewCreated: list: $list")

            if (list.isEmpty()) {
                setAddNowContainerVisibility(true)
            } else {
                setAddNowContainerVisibility(false)
                repoAdapter.submitList(list)
                repoAdapter.setOnItemClickListener { item ->
                    Snackbar.make(
                        requireView(),
                        "Clicked on ${item.repoUsername}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    private fun initRV() {
        repoAdapter = RepoListAdapter()
        binding.repoListRV.apply {
            adapter = repoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initClickListeners() {
        binding.addRepoButton.setOnClickListener {
            Snackbar.make(requireView(), "Clicked on ADD", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setAddNowContainerVisibility(isVisible: Boolean) {
        if (isVisible)
            binding.addNowContainer.visibility = View.VISIBLE
        else
            binding.addNowContainer.visibility = View.GONE
    }
}
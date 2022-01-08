package com.mrwhoknows.gh_browser.ui.list_page

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mrwhoknows.gh_browser.R
import com.mrwhoknows.gh_browser.databinding.FragmentRepoListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import com.google.android.material.appbar.MaterialToolbar

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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
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

    private fun setupToolbar() {
        val toolbar = requireActivity().findViewById<MaterialToolbar>(R.id.mainToolbar)
        toolbar.navigationIcon = null
        toolbar.title = findNavController().currentDestination?.label
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
            findNavController().navigate(
                R.id.action_repoListFragment_to_addRepoFragment
            )
        }
    }

    private fun setAddNowContainerVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.addNowContainer.visibility = View.VISIBLE
            binding.repoListRV.visibility = View.GONE
        } else {
            binding.addNowContainer.visibility = View.GONE
            binding.repoListRV.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                findNavController().navigate(
                    R.id.action_repoListFragment_to_addRepoFragment
                )
                return true
            }
        }
        return false
    }
}
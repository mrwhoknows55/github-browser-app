package com.mrwhoknows.gh_browser.ui.list_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mrwhoknows.gh_browser.data.Repository
import com.mrwhoknows.gh_browser.databinding.RepositoryItemBinding

class RepoListAdapter :
    ListAdapter<Repository, RepoListAdapter.RepoViewHolder>(RepositoryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = RepositoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repoList = this.currentList

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(repoList[position])
            }
        }
        if (repoList.isNotEmpty())
            holder.bind(repoList[position])
    }

    inner class RepoViewHolder(private val binding: RepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Repository) {
            binding.repoName.text = item.repoName
            binding.repoDesc.text =
                item.repoName + item.repoName + item.repoName + item.repoName + item.repoName
        }
    }

    private var onItemClickListener: ((Repository) -> Unit)? = null

    fun setOnItemClickListener(listener: (Repository) -> Unit) {
        onItemClickListener = listener
    }

    object RepositoryDiffCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
            oldItem == newItem
    }

}
package com.mrwhoknows.gh_browser.ui.list_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrwhoknows.gh_browser.data.Repository
import com.mrwhoknows.gh_browser.data.RepositoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RepositoryListViewModel
@Inject constructor(
    private val repositoryDao: RepositoryDao
) : ViewModel() {

    private val _repositories: MutableLiveData<List<Repository>> = MutableLiveData()
    val repositories: LiveData<List<Repository>> = _repositories

    fun getAllRepositories() {
        viewModelScope.launch {
            _repositories.value = repositoryDao.getAllRepositories()
        }
    }

    fun insertFakeRepo() {
        viewModelScope.launch {
            repositoryDao.insertRepository(
                Repository(
                    id = Random.nextInt(),
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString()
                )
            )
        }
    }
}
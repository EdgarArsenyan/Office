package com.example.office.models.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.office.repo.ItemsRepos

/**
 * Author by Edgar Arsenyan, Date on 16.02.2021.
Mobile +374 96-448-555
Email edgararsenyan@gmail.com
 * PS: Not easy to write code, please indicate.
 */
class ItemViewModelFactory(private val repository: ItemsRepos) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ItemViewModel::class.java)){
            return ItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
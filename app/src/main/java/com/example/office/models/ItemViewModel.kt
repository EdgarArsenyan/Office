package com.example.office.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.office.repo.ItemsRepos
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemsRepos, private val item: ItemModel): ViewModel() {

    val getItem = repository.items

    fun setItem(item: ItemModel):Job = viewModelScope.launch {
            repository.insert(item)
    }
}
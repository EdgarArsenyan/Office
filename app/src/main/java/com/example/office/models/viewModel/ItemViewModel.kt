package com.example.office.models.viewModel

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.office.models.CategoryModel
import com.example.office.models.ItemModel
import com.example.office.repo.ItemsRepos
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemsRepos): ViewModel(), Observable {

    val getItem = repository.items

    fun setItem(item: CategoryModel) = viewModelScope.launch {
            repository.insert(item)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}
package com.example.office.repo

import com.example.office.db.ItemsDao
import com.example.office.models.CategoryModel
import com.example.office.models.ItemModel

class ItemsRepos(private val dao : ItemsDao) {

    val items = dao.getAllItems()
    
    suspend fun insert(item : CategoryModel){
        dao.insertAll(item)
    }
}
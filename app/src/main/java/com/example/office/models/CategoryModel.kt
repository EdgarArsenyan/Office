package com.example.office.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "item_list"
)
data class CategoryModel(
    @PrimaryKey(autoGenerate = false)
    val i: Int,
    val ImageUrl: String,
    val categoryName: String
)
package com.example.office.models

import androidx.room.Entity
import androidx.room.PrimaryKey


data class ItemModel(
    val id: Int,
    val title:String,
    val price: String,
    val imageWay: String
)

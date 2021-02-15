package com.example.office.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "item_list"
)
data class ItemModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val price: String,
    val imageWay: String
)

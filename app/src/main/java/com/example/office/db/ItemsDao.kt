package com.example.office.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.office.models.ItemModel

@Dao
interface ItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: ItemModel)

    @Query("SELECT * FROM item_list")
    fun getAllItems() : LiveData<List<ItemModel>>
}
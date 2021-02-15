package com.example.office.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.office.models.ItemModel

@Database(entities = [ItemModel::class], version = 1, exportSchema = false)
abstract class ItemsDB : RoomDatabase() {

    abstract val itemsDap: ItemsDao

    @Volatile
    private var INSTANCE_DB: ItemsDB? = null
    fun getInstance(context: Context): ItemsDB {
        synchronized(this) {
            var instance = INSTANCE_DB
            if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemsDB::class.java,
                        "item_list"
                    ).build()
                }
            return instance
        }
    }
}
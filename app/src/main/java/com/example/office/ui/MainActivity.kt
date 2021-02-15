package com.example.office.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.office.R
import com.example.office.adapter.CategoryAdapter
import com.example.office.databinding.ActivityMainBinding
import com.example.office.models.CategoryModel
import com.example.office.models.Singleton
import jp.wasabeef.glide.transformations.BlurTransformation

class MainActivity : AppCompatActivity(), CategoryAdapter.ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CategoryAdapter
    private val list = mutableListOf<CategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        setBackGroundBlur()
        setList()
        checkRowCount()
        clickRowCount()
        initRecycler()
    }

    private fun setBackGroundBlur() {
        Glide.with(binding.root)
            .load(R.drawable.diva_screen)
            .apply(bitmapTransform(BlurTransformation(15, 3)))
            .into(binding.screenImage)
    }

    private  fun setList() {
        val a = CategoryModel("https://images.unsplash.com/photo-1612831204070-6cf189774a57?ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDIwfHRvd0paRnNrcEdnfHxlbnwwfHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60", "Love")
        list.add(a)
        val b = CategoryModel("https://images.unsplash.com/photo-1583843364289-0d1b2978874c?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MTUwfHxodW1hbnxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60", "Girls")
        list.add(b)
        val c = CategoryModel("https://images.unsplash.com/photo-1570140398191-0a9b1c02a767?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxzZWFyY2h8MTk5fHxodW1hbnxlbnwwfHwwfA%3D%3D&auto=format&fit=crop&w=500&q=60", "Architecture")
        list.add(c)
        val d = CategoryModel("https://images.unsplash.com/photo-1581522387607-5b88ed40aa57?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MTkwfHxodW1hbnxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60", "City")
        list.add(d)
        val e = CategoryModel("https://images.unsplash.com/photo-1501183007986-d0d080b147f9?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80", "Nature")
        list.add(e)
    }


    private fun initRecycler() {
        adapter = CategoryAdapter(list, this )
        binding.categoryRv.layoutManager = GridLayoutManager(this, Singleton.rowCount)
        binding.categoryRv.adapter = adapter
    }

    private fun checkRowCount() {
        when(Singleton.rowCount){
            1 ->   {
                binding.rowCountIcon.setImageResource(R.drawable.ic_filter_2_24)
            }
            2 -> {
                binding.rowCountIcon.setImageResource(R.drawable.ic_filter_3_24)
            }
            3 -> {
                binding.rowCountIcon.setImageResource(R.drawable.ic_filter_1_24)
            }

        }

    }

    private fun clickRowCount() {
        binding.rowCountIcon.setOnClickListener(View.OnClickListener {
            when(Singleton.rowCount){
                1 ->   {
                    binding.rowCountIcon.setImageResource(R.drawable.ic_filter_3_24)
                    ++Singleton.rowCount
                    initRecycler()
                }
                2 -> {
                    binding.rowCountIcon.setImageResource(R.drawable.ic_filter_1_24)
                    ++Singleton.rowCount
                    initRecycler()
                }
                3 -> {
                    binding.rowCountIcon.setImageResource(R.drawable.ic_filter_2_24)
                    Singleton.rowCount = 1
                    initRecycler()
                }

            }
        })
    }

    override fun onClickedItem(itemID: Int) {
            Singleton.itemImageUrl = list[itemID].ImageUrl
            Singleton.itemName = list[itemID].categoryName
            Log.e("tag ", Singleton.itemImageUrl)
            val intent = Intent(this, ItemActivity::class.java)
            startActivity(intent)
    }
}
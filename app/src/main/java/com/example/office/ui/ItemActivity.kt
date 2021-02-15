package com.example.office.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.office.R
import com.example.office.databinding.ActivityItemBinding
import com.example.office.models.Singleton
import jp.wasabeef.glide.transformations.BlurTransformation

class ItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        setBackGroundBlur()
        setItem()
    }

    private fun setItem() {
        binding.itemTitle.text = Singleton.itemName
        Glide.with(binding.root)
                .load(Singleton.itemImageUrl)
                .into(binding.itemImage)
    }

    private fun setBackGroundBlur() {
        Glide.with(binding.root)
                .load(R.drawable.diva_screen)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(15, 3)))
                .into(binding.itemScreenImage)
    }
}
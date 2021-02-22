package com.example.office.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.office.R
import com.example.office.databinding.FragmentItemBinding
import com.example.office.models.Singleton
import com.example.office.utils.Constants
import jp.wasabeef.glide.transformations.BlurTransformation

class ItemFragment : Fragment() {

    private lateinit var binding: FragmentItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(inflater, container, false)
        Singleton.fragmentName = Constants.ITEM_FR
        setBackGroundBlur()
        setItem()
        return binding.root    }


    private fun setItem() {
        binding.itemTitle.text = Singleton.itemName
        binding.itemPrice.text = Singleton.itemDesc
        binding.mainTitle.text = Singleton.itemName
        Glide.with(binding.root)
            .load(Singleton.itemImageUrl)
            .into(binding.itemImage)
    }

    private fun setBackGroundBlur() {
        Glide.with(binding.root)
            .load(R.drawable.screen)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(15, 3)))
            .into(binding.itemScreenImage)
    }

}
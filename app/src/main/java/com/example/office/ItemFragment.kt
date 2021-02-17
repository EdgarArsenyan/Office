package com.example.office

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.office.databinding.FragmentCategoryListBinding
import com.example.office.databinding.FragmentItemBinding
import com.example.office.models.Singleton
import jp.wasabeef.glide.transformations.BlurTransformation

class ItemFragment : Fragment() {

    private lateinit var binding: FragmentItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(inflater, container, false)
        setBackGroundBlur()
        setItem()
        binding.itemImage.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_itemFragment_to_categoryListFragment)
        })
        return binding.root    }

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
package com.example.office.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.office.databinding.CategoryLayoutBinding
import com.example.office.models.CategoryModel

class CategoryAdapter(private val posts: MutableList<CategoryModel>, private val listener: ItemClickListener): RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    interface ItemClickListener {
        fun onClickedItem(itemID: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val binding: CategoryLayoutBinding = CategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryVH(binding, listener)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) = holder.bind(posts[position])

    override fun getItemCount(): Int = posts.size

    class CategoryVH(private val itemBinding: CategoryLayoutBinding, private val listener: ItemClickListener) : RecyclerView.ViewHolder(itemBinding.root),
            View.OnClickListener{
        private lateinit var categoryModel: CategoryModel

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(categoryModel: CategoryModel)
        {
            this.categoryModel = categoryModel
            itemBinding.categoryText.text = categoryModel.categoryName
            Glide.with(itemBinding.root)
                    .load(categoryModel.ImageUrl)
                    .centerInside()
                    .into(itemBinding.categoryImage)
        }

        override fun onClick(v: View?) {
            listener.onClickedItem(position)
        }
    }
}


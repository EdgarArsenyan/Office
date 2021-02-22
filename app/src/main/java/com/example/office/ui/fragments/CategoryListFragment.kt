package com.example.office.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.office.R
import com.example.office.adapter.CategoryAdapter
import com.example.office.databinding.FragmentCategoryListBinding
import com.example.office.db.ItemsDB
import com.example.office.models.CategoryModel
import com.example.office.models.Singleton
import com.example.office.models.viewModel.ItemViewModel
import com.example.office.models.viewModel.ItemViewModelFactory
import com.example.office.repo.ItemsRepos
import com.example.office.utils.Constants
import jp.wasabeef.glide.transformations.BlurTransformation
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CategoryListFragment : Fragment(), CategoryAdapter.ItemClickListener{

    private lateinit var binding: FragmentCategoryListBinding
    private lateinit var adapter: CategoryAdapter
    private val list = mutableListOf<CategoryModel>()
    private var myList = mutableListOf<CategoryModel>()
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        Singleton.fragmentName = Constants.CATEGORY_FR
        setBackGroundBlur()
        setList()
        initRoom()
        displayItemList()
        checkRowCount()
        clickRowCount()
        return binding.root
    }

    private fun downloadImage(imageURL: String?) {
        Glide.with(requireContext())
            .load(imageURL)
            .into(object : CustomTarget<Drawable?>() {

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    val bitmap = (resource as BitmapDrawable).bitmap
                    saveImage(bitmap)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    Log.e("saving  ", errorDrawable.toString())
                }


            })
    }

    private fun saveImage(image: Bitmap) {
        val dirPath = Environment.getExternalStorageDirectory().absolutePath + "/" + "News" + "/"
        val dir = File(dirPath)
        if (!dir.isDirectory) {
            dir.mkdir()
        }
        val fileName = System.currentTimeMillis().toString() + ".jpg"
        val imageFile = File(dir, fileName)
        try {
            val fOut: OutputStream = FileOutputStream(imageFile)
            image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
//            model.setImageUrlModel(imageFile.absolutePath)
//            val viewModel: UploadViewModel =
//                ViewModelProviders.of((context as FragmentActivity?)!!).get(
//                    UploadViewModel::class.java
//                )
//            viewModel.insert(model)
            fOut.close()
        } catch (e: Exception) {
            Log.e("saving  ", Objects.requireNonNull(e.message))
            e.printStackTrace()
        }
    }

    private fun initRoom() {
        val dao = ItemsDB.getInstance(requireContext()).itemsDao
        val repository = ItemsRepos(dao)
        val factory =  ItemViewModelFactory(repository)
        itemViewModel = ViewModelProvider(this, factory)[ItemViewModel::class.java]
        for (item in list){
//            downloadImage(item.ImageUrl)
            itemViewModel.setItem(item)
        }
    }

    private fun displayItemList(){
        itemViewModel.getItem.observe(viewLifecycleOwner, Observer {
            myList = it as MutableList<CategoryModel>
            initRecycler()
        })
    }

        private fun setBackGroundBlur() {
        Glide.with(binding.root)
            .load(R.drawable.screen)
            .apply(bitmapTransform(BlurTransformation(15, 3)))
            .into(binding.screenImage)
    }

    private  fun setList() {
        val a = CategoryModel(
            1,
            "https://scontent.fevn1-4.fna.fbcdn.net/v/t1.15752-9/150824652_479884510103006_7040602418932880397_n.jpg?_nc_cat=107&ccb=3&_nc_sid=ae9488&_nc_ohc=FN1npijeGIIAX8kGdxC&_nc_ht=scontent.fevn1-4.fna&oh=266e11aa5db074d222461ffe6d354fd5&oe=6055FC1A",
            "Ласось",
            "5000 ДРАМ"
        )
        list.add(a)
        val b = CategoryModel(
            2,
            "https://scontent.fevn1-4.fna.fbcdn.net/v/t1.15752-9/151177190_127341629280933_7575045282640915574_n.jpg?_nc_cat=104&ccb=3&_nc_sid=ae9488&_nc_ohc=AiQvgzcFtkIAX8qKJnM&_nc_ht=scontent.fevn1-4.fna&oh=4274850e4b1836eb5e3b052f622ef648&oe=605481D2",
            "Суп",
            "1500 ДРАМ"
        )
        list.add(b)
        val c = CategoryModel(
            3,
            "https://scontent.fevn1-4.fna.fbcdn.net/v/t1.15752-9/150449382_913950249414650_706928402686027719_n.jpg?_nc_cat=109&ccb=3&_nc_sid=ae9488&_nc_ohc=-d3zkKbYxn8AX-Qoi0x&_nc_ht=scontent.fevn1-4.fna&oh=e853b38a6bc35576a1f87303a6d667b5&oe=6056CEDB",
            "Бутерброд",
            "2000 ДРАМ"
        )
        list.add(c)
        val d = CategoryModel(
            4,
            "https://scontent.fevn1-4.fna.fbcdn.net/v/t1.15752-9/150958958_918348122143407_2105748329565072823_n.jpg?_nc_cat=100&ccb=3&_nc_sid=ae9488&_nc_ohc=T43ytPQ5PxUAX9Fwguw&_nc_ht=scontent.fevn1-4.fna&oh=9032ba502f71780bdca0ac26693f0f19&oe=605695DB",
            "Мясное ассорти",
            "5000 ДРАМ"
        )
        list.add(d)
        val e = CategoryModel(
            5,
            "https://scontent.fevn1-4.fna.fbcdn.net/v/t1.15752-9/150965241_1134995373616228_5115505726964530417_n.jpg?_nc_cat=101&ccb=3&_nc_sid=ae9488&_nc_ohc=MYw2Xy2xNpcAX_XAyhm&_nc_ht=scontent.fevn1-4.fna&oh=109ebbe55d2d06d093dfa0c429f2f6e8&oe=6053A2C1",
            "Сырное ассорти",
            "4400 ДРАМ"
        )
        list.add(e)
        val f = CategoryModel(
            6,
            "https://scontent.fevn1-4.fna.fbcdn.net/v/t1.15752-9/150460221_228491525616598_6365073434388165812_n.jpg?_nc_cat=104&ccb=3&_nc_sid=ae9488&_nc_ohc=jobqAz0mxcEAX_yNUGF&_nc_oc=AQmRdcTpKpWO8LIEraBegwOBWh4Ui6rOzSWd7tqh2dVYLfGiOsuDjBFhHnccHGWomDs&_nc_ht=scontent.fevn1-4.fna&oh=4f0df4eadcb766067159621ffccdae2d&oe=60548B3D",
            "Хот-Дог",
            "1600 ДРАМ"
        )
        list.add(f)
        val i = CategoryModel(
            7,
            "https://scontent.fevn1-4.fna.fbcdn.net/v/t1.15752-9/150718482_190055819576414_5795522879660532411_n.jpg?_nc_cat=110&ccb=3&_nc_sid=ae9488&_nc_ohc=VOTFRoD7y1oAX9dqcaB&_nc_ht=scontent.fevn1-4.fna&oh=06e2b8a38f93c41414b539fcbf4fdc1c&oe=605618E3",
            "Хлеб",
            "500 ДРАМ"
        )
        list.add(i)
    }


    private fun initRecycler() {
        adapter = CategoryAdapter(myList, this)
        binding.categoryRv.layoutManager = GridLayoutManager(context, Singleton.rowCount)
        binding.categoryRv.adapter = adapter
    }

    private fun checkRowCount() {
        when(Singleton.rowCount){
            1 -> {
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
            when (Singleton.rowCount) {
                1 -> {
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
        Singleton.itemDesc = list[itemID].categoryDesc
        Log.e("tag ", Singleton.itemImageUrl)
        findNavController().navigate(R.id.action_categoryListFragment_to_itemFragment)
    }

}
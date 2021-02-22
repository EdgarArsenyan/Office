package com.example.office.ui.activitis

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.office.databinding.ActivityMainBinding
import com.example.office.models.Singleton
import com.example.office.utils.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
    }
    override fun onBackPressed() {

        when(Singleton.fragmentName){
            Constants.ITEM_FR -> super.onBackPressed()
            Constants.CATEGORY_FR -> return
        }
    }

}
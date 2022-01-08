package com.mrwhoknows.gh_browser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrwhoknows.gh_browser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
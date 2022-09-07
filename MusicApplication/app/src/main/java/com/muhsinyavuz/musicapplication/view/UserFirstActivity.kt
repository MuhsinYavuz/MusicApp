package com.muhsinyavuz.musicapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muhsinyavuz.musicapplication.R
import com.muhsinyavuz.musicapplication.databinding.ActivityMainBinding
import com.muhsinyavuz.musicapplication.databinding.ActivityUserFirstBinding
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_signin.view.*
import kotlinx.android.synthetic.main.item_music_row.view.*

class UserFirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserFirstBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}
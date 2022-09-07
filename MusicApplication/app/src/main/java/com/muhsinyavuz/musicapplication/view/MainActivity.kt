package com.muhsinyavuz.musicapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.muhsinyavuz.musicapplication.R
import com.muhsinyavuz.musicapplication.databinding.ActivityMainBinding
import com.muhsinyavuz.musicapplication.util.toast
import com.muhsinyavuz.musicapplication.viewModel.MusicListViewModel
import kotlinx.android.synthetic.main.item_music_row.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)





    }
/*

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.filter_music,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }


 */
}
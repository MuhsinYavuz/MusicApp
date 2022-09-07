package com.muhsinyavuz.musicapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.muhsinyavuz.musicapplication.R
import com.muhsinyavuz.musicapplication.databinding.FragmentMusicDetailBinding
import com.muhsinyavuz.musicapplication.databinding.FragmentMusicListBinding
import com.muhsinyavuz.musicapplication.util.downloadFromUrl
import com.muhsinyavuz.musicapplication.util.downloadFromUrl2
import com.muhsinyavuz.musicapplication.util.placeholderProgressBar
import com.muhsinyavuz.musicapplication.viewModel.MusicDetailViewModel


class MusicDetailFragment : Fragment() {

    private lateinit var binding: FragmentMusicDetailBinding

    private lateinit var viewModel: MusicDetailViewModel

    var imageId: String? = null
    var name: String? = null
    var artistName : String? = null
    var musicType : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //    return inflater.inflate(R.layout.fragment_music_detail, container, false)
        binding = FragmentMusicDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MusicDetailViewModel::class.java]
        arguments?.let {
            imageId = MusicDetailFragmentArgs.fromBundle(it).imageId
            name = MusicDetailFragmentArgs.fromBundle(it).name
            artistName = MusicDetailFragmentArgs.fromBundle(it).artistName
            musicType = MusicDetailFragmentArgs.fromBundle(it).musicType
        }
        context?.let { placeholderProgressBar(it) }?.let {
            binding.musicDetailImage.downloadFromUrl(imageId,
                it
            )
        }

        binding.musicDetailName.text = name.orEmpty()
        binding.musicDetailArtistName.text = artistName.orEmpty()
        binding.musicDetailType.text = musicType.orEmpty()
        //binding.musicDetailImage.downloadFromUrl2(imageId)
        binding.exitUser.setOnClickListener {
            val intent = Intent(context,UserFirstActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

    }
    companion object{
        var name = "Muhsin"

    }

}
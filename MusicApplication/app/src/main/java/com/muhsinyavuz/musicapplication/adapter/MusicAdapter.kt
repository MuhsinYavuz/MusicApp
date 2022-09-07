package com.muhsinyavuz.musicapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.muhsinyavuz.musicapplication.R
import com.muhsinyavuz.musicapplication.databinding.ItemMusicRowBinding
import com.muhsinyavuz.musicapplication.model.Response
import com.muhsinyavuz.musicapplication.model.Result
import com.muhsinyavuz.musicapplication.util.downloadFromUrl
import com.muhsinyavuz.musicapplication.util.placeholderProgressBar
import com.muhsinyavuz.musicapplication.view.MusicListFragmentDirections
import java.util.*
import kotlin.collections.ArrayList


class MusicAdapter
    : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    private var musicList = arrayListOf<Result>()
    var str : String? =null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
      //  val inflater = LayoutInflater.from(parent.context)
       // val view = inflater.inflate(R.layout.item_music_row,parent,false) // recyclerda gösterilecek bağlantı kuruldu .
        val binding = ItemMusicRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.binding.musicName.text = musicList[position].name.toString()
        // movieList.forEach{ println("movelistrtrt$it")}
        // holder.itemView.movieType.text = movieList[position].feed.results.get(position).artistId.toString()
        holder.binding.musicType.text =musicList[position].artistName.toString()
        holder.itemView.setOnClickListener {
            val action = MusicListFragmentDirections.actionMusicListFragmentToMusicDetailFragment(musicList[position].artworkUrl100,
                musicList[position].name,musicList[position].artistName,musicList[position].genres!![0].name)
            Navigation.findNavController(it).navigate(action)
            }
       holder.binding.musicImage.downloadFromUrl(musicList[position].artworkUrl100,
           placeholderProgressBar((holder.itemView.context)))
    }

    fun updateMovieList(newMovieList : ArrayList<Result>){
        musicList.clear()
        musicList.addAll(newMovieList)
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int = musicList.size


    class MusicViewHolder(var binding : ItemMusicRowBinding) : RecyclerView.ViewHolder(binding.root) {

/*

        fun bind(item: Result) {
            itemView.apply {
                binding.musicName.text = item.name
                // movieList.forEach{ println("movelistrtrt$it")}
                // holder.itemView.movieType.text = movieList[position].feed.results.get(position).artistId.toString()
                musicType.text = item.artistName
                musicType.setOnClickListener {
                    val action =
                        MusicListFragmentDirections.actionMusicListFragmentToMusicDetailFragment()
                    Navigation.findNavController(it).navigate(action)

                }
                musicImage.downloadFromUrl(
                    item.artworkUrl100,
                    placeholderProgressBar((context))
                )
            }
        }
*/
        // apply ya da with

        /* with(itemView){

         }*/


    }
}
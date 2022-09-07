package com.muhsinyavuz.musicapplication.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhsinyavuz.musicapplication.R
import com.muhsinyavuz.musicapplication.adapter.MusicAdapter
import com.muhsinyavuz.musicapplication.databinding.FragmentMusicListBinding
import com.muhsinyavuz.musicapplication.model.Result
import com.muhsinyavuz.musicapplication.viewModel.MusicListViewModel




class MusicListFragment : Fragment() {
    private var _binding: FragmentMusicListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : MusicListViewModel
    private  var musicAdapter = MusicAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
      //  setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_music_list, container, false)
        _binding = FragmentMusicListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.filter_music,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_action -> {
                println("tıklandı search")
            }
        }
        return true
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MusicListViewModel::class.java]
        //hangi viewModelSınıfı ile çalışacağız belirtiyoruz.
        viewModel.refreshData() // verilerin geleceği methot
        // viewModel.getDataFromApi()
        binding.musicList.layoutManager = LinearLayoutManager(context)
        binding.musicList.adapter = musicAdapter // adapter tanımlaması yapıldı .
        binding.swipeRefreshLayout.setOnRefreshListener{
            binding.musicList.visibility = View.GONE
            binding.movieError.visibility = View.GONE
            binding.musicListLoadingBar.visibility = View.VISIBLE
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false // sistemdeki swipeRes kapattık

        }
        observeLiveData()


    }
    // gözlemci bağlantısı kuruldu.
    private fun observeLiveData(){

        // viewModel(MovieListViewModel) movies isimli listeden gözlemleme yapıcak
        viewModel.musics.observe(viewLifecycleOwner,Observer{ musics ->
            musics?.let {
                binding.musicList.visibility = View.VISIBLE
                musicAdapter.updateMovieList(musics.feed.results) //

            }
        })


        // eğer error verrise error yazısını çıkması sağlanacak .
        viewModel.musicListError.observe(viewLifecycleOwner,Observer{ error ->
            error?.let {
                if(it){
                    binding.movieError.visibility = View.VISIBLE
                }else {
                    binding.movieError.visibility = View.GONE
                }
            }
        })
        viewModel.musicLoading.observe(viewLifecycleOwner,Observer{loading ->
            loading?.let {
                if(it){
                    binding.musicListLoadingBar.visibility = View.VISIBLE
                    binding.musicList.visibility = View.GONE
                    binding.movieError.visibility = View.GONE
                }else {
                    binding.musicListLoadingBar.visibility = View.GONE
                }
            }
        }
        )
    }

}


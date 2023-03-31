package com.axiom.tmdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.axiom.tmdb.FavoriteManager
import com.axiom.tmdb.MainActivity
import com.axiom.tmdb.MyApi
import com.axiom.tmdb.TMDB
import com.axiom.tmdb.adapters.TVShowsAdapter
import com.axiom.tmdb.views.SearchedTVShowsView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchedTVShowsFragment:Fragment() {
    private lateinit var tvShows: MutableList<TMDB.TVShowBasic>
    //lateinit var topRatedMoviesRecyclerView: RecyclerView
    var searchTVShow:String=""
    object RetrofitHelper {
        private const val baseUrl = "https://api.themoviedb.org/3/search/"
        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            if (it != null) {
                searchTVShow = it.getString("tvShow")!!
            }
        }
        tvShows= mutableListOf()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.context.let {
        SearchedTVShowsView(it).apply {
            val myApi = RetrofitHelper.getInstance().create(MyApi::class.java)
            lifecycleScope.launchWhenResumed {
                var response= myApi.searchTVShow("287f6ab6616e3724955e2b4c6841ea63",searchTVShow)

                var results = response.body()!!.results
                for (x in results!!) {
                    tvShows.add(x)
                }
                var favorites= ViewModelProvider((activity as MainActivity))[FavoriteManager::class.java]
                var f=favorites
                recyclerView.layoutManager =
                    LinearLayoutManager(context)
                recyclerView.adapter =
                    TVShowsAdapter(tvShows, favorites,{ tvShowID ->
                        for (x in tvShows) {
                            if (x.id == tvShowID) {
                                println(tvShowID)
                                var tvShowFragment = TVShowFragment.newInstance(tvShowID)

                                (activity as? MainActivity)?.myLayout?.id?.let { it1 ->

                                    var transaction =
                                        activity?.supportFragmentManager?.beginTransaction()
                                    transaction?.replace(it1, tvShowFragment)?.commit()
                                    transaction?.addToBackStack(null)
                                }
                                break
                            }
                        }
                    },{
                            tvShow->
                        f.addTVShowFavorites(tvShow)
                    },{ tvShow->
                        f.deleteTVShowFavorites(tvShow)
                    },context)


                var favObserver= Observer<List<Triple<Int, String, String>>> {updated->
                    println("Updated "+updated)
                    var adapter=(recyclerView.adapter as TVShowsAdapter)
                    adapter.fav=updated
                    adapter.notifyDataSetChanged()
                }
                favorites.getTVShowsFavorites().observe(viewLifecycleOwner,favObserver)

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance(tvShow: String) =
            SearchedTVShowsFragment().apply {
                arguments = Bundle().apply {
                    putString("tvShow",tvShow)
                }
            }
    }
}
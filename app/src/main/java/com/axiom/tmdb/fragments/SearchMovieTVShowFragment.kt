package com.axiom.tmdb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.axiom.tmdb.MainActivity
import com.axiom.tmdb.views.SearchMovieTVShowView


class SearchMovieTVShowFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return SearchMovieTVShowView(inflater.context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view as SearchMovieTVShowView).apply {
            tvShowButton.setOnClickListener {
                var searchedTVShowFragment =
                    SearchedTVShowsFragment.newInstance(view.tvShowInputBox.text.toString())

                (activity as? MainActivity)?.myLayout?.id?.let { it1 ->

                    var transaction =
                        activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(it1, searchedTVShowFragment)?.commit()
                    transaction?.addToBackStack(null)
                }
            }
            movieButton.setOnClickListener {
                var searchedMovieFragment =
                    SearchedMoviesFragment.newInstance(view.movieInputBox.text.toString())

                (activity as? MainActivity)?.myLayout?.id?.let { it1 ->

                    var transaction =
                        activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(it1, searchedMovieFragment)?.commit()
                    transaction?.addToBackStack(null)
                }
            }
        }
    }


}
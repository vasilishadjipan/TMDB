package com.axiom.tmdb.views

import com.axiom.tmdb.views.shimmer.RecyclerViewShimmer
import android.content.Context
import android.graphics.Color
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.axiom.tmdb.MyItemDecoration
import com.axiomc.core.dslanguage.constraint.Helpers.applyId
import com.axiomc.core.dslanguage.design.Text.bold
import com.axiomc.core.dslanguage.design.Text.size
import com.axiomc.core.dslanguage.design.Text.text
import com.axiomc.core.dslanguage.design.color.Theme.color

class TopRatedTVShowsView(context: Context) : ConstraintLayout(context) {
    val title = TextView(context).applyId().bold().color(Color.BLACK).size(20)

    val tvShowsRecyclerView = RecyclerView(context).applyId()
    val shimmer= RecyclerViewShimmer(context).applyId()


    init {

        applyId()
        setBackgroundColor(Color.WHITE)
        layoutParams= LayoutParams(MATCH_PARENT, MATCH_PARENT)
        title.layoutParams=LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToTop=this@TopRatedTVShowsView.id
            startToStart=this@TopRatedTVShowsView.id
            endToEnd=this@TopRatedTVShowsView.id
            setMargins(20,0,20,0)
        }
        addView(title)
        tvShowsRecyclerView.layoutParams =
            LayoutParams(MATCH_PARENT, 0).apply {
                topToBottom=title.id
                startToStart=this@TopRatedTVShowsView.id
                endToEnd=this@TopRatedTVShowsView.id
                bottomToBottom=this@TopRatedTVShowsView.id
            }
        tvShowsRecyclerView.addItemDecoration(MyItemDecoration(20,20,20,0))

        addView(tvShowsRecyclerView)

        shimmer.layoutParams =
            LayoutParams(MATCH_PARENT, 0).apply {
                topToBottom=title.id
                startToStart=this@TopRatedTVShowsView.id
                endToEnd=this@TopRatedTVShowsView.id
                bottomToBottom=this@TopRatedTVShowsView.id
            }
        tvShowsRecyclerView.visibility= INVISIBLE
        addView(shimmer)

    }

}
package com.axiom.tmdb

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginRight
import com.axiomc.core.dslanguage.design.Text.bold
import com.axiomc.core.dslanguage.design.Text.size
import com.axiomc.core.dslanguage.design.color.Theme.color

class TitleDescriptionView(context: Context):ConstraintLayout(context){
    var title=TextView(context).bold().color(Color.BLACK).size(14)
    var desc=TextView(context)
    fun vertical():TitleDescriptionView{
        title.layoutParams=ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop=this@TitleDescriptionView.id
            startToStart=this@TitleDescriptionView.id

        }
        desc.layoutParams=ConstraintLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT).apply {
            topToBottom=title.id
            bottomToBottom=this@TitleDescriptionView.id
            startToStart=this@TitleDescriptionView.id

        }
        return this
    }
    fun horizontal():TitleDescriptionView{
        title.layoutParams=ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop=this@TitleDescriptionView.id
            bottomToBottom=this@TitleDescriptionView.id
            startToStart=this@TitleDescriptionView.id
        }
        desc.layoutParams=ConstraintLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT).apply {
            topToTop=this@TitleDescriptionView.id
            bottomToBottom=this@TitleDescriptionView.id
            startToEnd=title.id
            marginStart=20
        }
        return this
    }
    init{
        id= View.generateViewId()
        layoutParams= LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        title.id=View.generateViewId()
        desc.id=View.generateViewId()
        addView(title)
        addView(desc)
    }
}
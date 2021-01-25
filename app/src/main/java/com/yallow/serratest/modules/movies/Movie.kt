package com.yallow.serratest.modules.movies

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yallow.serratest.R
import com.yallow.serratest.databinding.RowMovieBinding
import com.yallow.serratest.master.MasterActivity
import com.yallow.serratest.modules.master.MasterRecycleViewModule
import com.yallow.serratest.ui.activites.MovieDetailsActivity
import com.yallow.serratest.utils.Constants.MOVIE_OBJECT


/**
 * @author mohammad rababah
 * desc movie response object
 */

class Movie  : MasterRecycleViewModule(){

    var adult = false
    var backdrop_path = ""
    var genre_ids = ArrayList<Int>()
    var id = -1
    var original_language = ""
    var original_title = ""
    var overview = ""
    var popularity = 2942.16
    var poster_path = ""
    var release_date = ""
    var title = ""
    var video = false
    var vote_average = 7.1
    var vote_count = 2853
    override var rowLayouts= ArrayList<Int>().apply {
        add(R.layout.row_movie)
    }


    override fun onBindViewHolder(masterActivity: MasterActivity, holder: RecyclerView.ViewHolder) {
        DataBindingUtil.bind<RowMovieBinding>(holder.itemView)!!.movie = this
       holder.itemView.setOnClickListener {
           val intent = Intent(masterActivity,MovieDetailsActivity::class.java)
           intent.putExtra(MOVIE_OBJECT,this)
           masterActivity.startActivity(intent)
       }

    }

    override fun toString(): String {
        return title
    }
}
package com.yallow.serratest.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yallow.serratest.R
import com.yallow.serratest.databinding.ActivityMovieDetailsBinding
import com.yallow.serratest.modules.movies.Movie
import com.yallow.serratest.utils.Constants.MOVIE_OBJECT
/**
 * @author mohammad rababah
 * desc movie full description activity
 */


class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     val bind =    DataBindingUtil.setContentView<ActivityMovieDetailsBinding>(this,R.layout.activity_movie_details)
    bind.movie = intent.getSerializableExtra(MOVIE_OBJECT) as Movie? ?: Movie()
    }
}
package com.yallow.serratest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yallow.serratest.R
import com.yallow.serratest.manegrs.gateway.GatewayMangerCaller
import com.yallow.serratest.manegrs.gateway.body.MovieBody
import com.yallow.serratest.master.MasterActivity
import com.yallow.serratest.master.MasterRecycleViewPagingAdapter
import com.yallow.serratest.modules.movies.MovieResponse
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {
    /**
     * @author mohammad rababah
     * desc main fragment with factory design pattern (which is the default)
     */

    companion object {
        fun newInstance() = MainFragment()
    }

    private val popularMovieBody = MovieBody().apply { sortBy = MovieBody.MovieSortBy.POPULAR }
    private val topRatedMovieBody = MovieBody().apply { sortBy = MovieBody.MovieSortBy.TOP_RATED }
    private val revenueMovieBody = MovieBody().apply { sortBy = MovieBody.MovieSortBy.REVENUE }
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.callTopRatedRequest(topRatedMovieBody)
        viewModel.callPopularRequest(popularMovieBody)
        viewModel.callRevenueRequest(revenueMovieBody)


        assignObservers()
    }

    /**
     *  @author mohammad rababah
     * desc  handle observation when the data received and if it changed (best practice to notify single row not all)
     */
    private fun assignObservers() {
        viewModel.topRatedList.observe(this,
            Observer<MovieResponse> {
                view?.mainFragmentTopRatedLoaderRecycleView?.adapter =
                    MasterRecycleViewPagingAdapter(
                        activity as MasterActivity,
                        it,
                        topRatedMovieBody,
                        GatewayMangerCaller.getInstance()
                            .initializeMovieRequest(topRatedMovieBody.loadHashMapParam())
                    )
            })



        viewModel.popularList.observe(this,
            Observer<MovieResponse> {
                view?.mainFragmentPopularLoaderRecycleView?.adapter =
                    MasterRecycleViewPagingAdapter(
                        activity as MasterActivity,
                        it,
                        popularMovieBody,
                        GatewayMangerCaller.getInstance()
                            .initializeMovieRequest(popularMovieBody.loadHashMapParam())
                    )
            })


        viewModel.revenueList.observe(this,
            Observer<MovieResponse> {
                view?.mainFragmentRevenueLoaderRecycleView?.adapter =
                    MasterRecycleViewPagingAdapter(
                        activity as MasterActivity,
                        it,
                        revenueMovieBody,
                        GatewayMangerCaller.getInstance()
                            .initializeMovieRequest(revenueMovieBody.loadHashMapParam())
                    )
            })
    }

}
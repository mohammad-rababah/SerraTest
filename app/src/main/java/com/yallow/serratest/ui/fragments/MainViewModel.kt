package com.yallow.serratest.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yallow.serratest.manegrs.gateway.GatewayMangerCaller
import com.yallow.serratest.manegrs.gateway.MasterResponse
import com.yallow.serratest.manegrs.gateway.body.MovieBody
import com.yallow.serratest.modules.movies.Movie
import com.yallow.serratest.modules.movies.MovieResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async

class MainViewModel : ViewModel() {
    private val gatewayMangerCaller  =  GatewayMangerCaller.getInstance()
    val   topRatedList : MutableLiveData<MovieResponse> =  MutableLiveData()
    val   revenueList : MutableLiveData<MovieResponse> =  MutableLiveData()
    val   popularList : MutableLiveData<MovieResponse> =  MutableLiveData()
    /**
     *  @author mohammad rababah
     * desc  call top rated api async
     */
    fun callTopRatedRequest(movieBody: MovieBody) {
        GlobalScope.async {

            gatewayMangerCaller.callRequest(gatewayMangerCaller.initializeMovieRequest(movieBody.loadHashMapParam())
                ,object : MasterResponse<MovieResponse>{
                    override fun onSuccess(responseObject: MovieResponse?) {
                        topRatedList.value = responseObject ?: MovieResponse()
                    }

                })
        }

    }
    /**
     *  @author mohammad rababah
     * desc  call popular api async
     */
    fun callPopularRequest(movieBody: MovieBody) {
        GlobalScope.async {

            gatewayMangerCaller.callRequest(gatewayMangerCaller.initializeMovieRequest(movieBody.loadHashMapParam())
                ,object : MasterResponse<MovieResponse>{
                    override fun onSuccess(responseObject: MovieResponse?) {
                        popularList.value = responseObject ?: MovieResponse()
                    }

                })
        }

    }
    /**
     *  @author mohammad rababah
     * desc  call revenue api async
     */
    fun callRevenueRequest(movieBody: MovieBody) {
        GlobalScope.async {

            gatewayMangerCaller.callRequest(gatewayMangerCaller.initializeMovieRequest(movieBody.loadHashMapParam())
                ,object : MasterResponse<MovieResponse>{
                    override fun onSuccess(responseObject: MovieResponse?) {
                        revenueList.value =responseObject ?: MovieResponse()
                    }

                })
        }
    }

}
package com.yallow.serratest.manegrs.gateway

import com.yallow.serratest.manegrs.gateway.ApiConstants.API_KEY
import com.yallow.serratest.manegrs.gateway.ApiConstants.DISCOVER_MOVIE
import com.yallow.serratest.modules.movies.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * @author mohammad rababah
 * desc retofit interface hold all requests
 *
 */
interface GateWayInterface {

    @GET(DISCOVER_MOVIE)
    fun discoverMovies(@QueryMap options  : HashMap<String,String>, @Query("api_key") api_key:String = API_KEY): Call<MovieResponse>

}
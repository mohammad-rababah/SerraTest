package com.yallow.serratest.manegrs.gateway

import com.google.gson.Gson
import com.yallow.serratest.manegrs.gateway.ApiConstants.BASE_URL
import com.yallow.serratest.manegrs.gateway.ApiConstants.TIME_OUT
import com.yallow.serratest.modules.ErrorModule
import com.yallow.serratest.modules.movies.MovieResponse
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.util.concurrent.TimeUnit


/**
 * @author mohammad rababah
 * desc retofit manger caller for rest api
 *
 */
class GatewayMangerCaller private constructor() {

    private val interceptor = HttpLoggingInterceptor()
    private val retrofit: Retrofit
    private val gatWayIntergaceInterface: GateWayInterface


    /**
     * @author mohammad rababah
     * desc init retrofit data with logging for debug
     *
     */

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val cookieHandler = CookieManager()
        val client = OkHttpClient.Builder().cookieJar(JavaNetCookieJar(cookieHandler))
            .addInterceptor(interceptor).connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS).writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
        retrofit = Retrofit.Builder().client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        gatWayIntergaceInterface = retrofit.create(GateWayInterface::class.java)

    }


    /**
     * @author mohammad rababah
     * desc call any retrofit api and handle response to pass it to the view model
     * @param request retrofit caller function
     * @param T type of response
     * @param masterResponse interface listener to pass response
     */
    fun <T> callRequest(request: Call<T>, masterResponse: MasterResponse<T>? = null) {

        request.clone().enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>?, t: Throwable?) {
                masterResponse?.onNetworkFail(t)
            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {

                if (response!!.isSuccessful)
                    masterResponse?.onSuccess(response.body())
                else
                    masterResponse?.onResponseError(handleError(response))
            }
        })
    }
    /**
     * @author mohammad rababah
     * desc call any retrofit api to handle error backend response
     * @param response retrofit error response
     * @param T type of response
     */
    private fun <T> handleError(response: Response<T>): ErrorModule? {
        val responseBody = response.errorBody()
        return try {
            val defaultModule = Gson().fromJson(responseBody?.string(), ErrorModule::class.java)
            defaultModule
        } catch (e: Exception) {
            ErrorModule().apply {
                statusMessage = responseBody?.string().toString()
            }
        }
    }

    /**
     * @author mohammad rababah
     * desc discover movie caller
     * @param hashMap key values that sent to the server
     * @return retrofit movie response caller
     */
    fun initializeMovieRequest(hashMap: HashMap<String, String>): Call<MovieResponse> {
        return gatWayIntergaceInterface.discoverMovies(hashMap)

    }
    /**
     * @author mohammad rababah
     * desc singleton design patter for the manger
     */
    companion object {
        fun getInstance() = GatewayMangerCaller()
    }

}
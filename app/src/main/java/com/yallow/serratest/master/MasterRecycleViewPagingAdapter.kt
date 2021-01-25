package com.yallow.serratest.master



import androidx.recyclerview.widget.RecyclerView
import com.yallow.serratest.modules.master.MasterPagingBody
import com.yallow.serratest.modules.master.MasterRecycleViewModule
import com.yallow.serratest.modules.master.MasterRecycleViewResponse
import com.yallow.serratest.ui.views.SerraLoaderRecycleView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/**
 * @author mohammad rababah
 * desc general recycle view adapter with paging mechanisim this class is really hlepfull when there
 * more than one paging data with the same architicher
 * @param activity an activity to use as context or ui events
 * @param masterRecycleViewPagingModule the data that should be adapte on it
 * @param T module class that will hold drawing
 * @param E master paging response module that hold list data and total count (end value)
 * @param masterPagingBody object of data params that hold api values
 * @param call api retofit caller to call next page of data
 *
 * @see SerraLoaderRecycleView that hold paging as view
 * @see MasterRecycleViewAdapter first
 */
open class MasterRecycleViewPagingAdapter<T : MasterRecycleViewModule, E : MasterRecycleViewResponse<T>>(override var activity: MasterActivity, var masterRecycleViewPagingModule: MasterRecycleViewResponse<*>, var masterPagingBody: MasterPagingBody, var call: Call<E>) : MasterRecycleViewAdapter<T>(activity,
    masterRecycleViewPagingModule.results as ArrayList<T>
), Callback<E> {

   lateinit var loaderRecyclerView: SerraLoaderRecycleView
    var isLoading = false
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.loaderRecyclerView = recyclerView.parent.parent as SerraLoaderRecycleView
    }
/**
 * @author mohammad rababah
 * desc when the scrolling reach last five item make more requests
 *
 * @see loadMoreItems
 */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position > arrayList.size - 5 && arrayList.size < masterRecycleViewPagingModule.total_results)
            loadMoreItems()
        super.onBindViewHolder(holder, position)
    }
    /**
     * @author mohammad rababah
     * desc this function copy the call request and add one page also reponse to show the progress bar
     *
     * @see loadMoreItems
     */
    private fun loadMoreItems() {
        if (!isLoading) {
            isLoading = true
            masterPagingBody.page += 1
            call.clone().enqueue(this)
            loaderRecyclerView.showButtonProgress()
        }
    }

    /**
     * @author mohammad rababah
     * desc handle api response if it return from server
     */
    override fun onResponse(call: Call<E>, response: Response<E>) {
        isLoading = false
        loaderRecyclerView.hideButtonProgress()
        if (response.isSuccessful) {
            val array = response.body()!!.results
            if(response.body()!!.results.size==0){
                masterRecycleViewPagingModule.total_results=arrayList.size
            }
            arrayList.addAll(array)
            notifyDataSetChanged()
        }
    }
    /**
     * @author mohammad rababah
     * desc handle api fail to reach server
     */
    override fun onFailure(call: Call<E>, t: Throwable) {
        isLoading = false
        loaderRecyclerView.hideButtonProgress()

    }

}
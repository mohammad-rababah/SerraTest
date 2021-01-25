package com.yallow.serratest.master

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yallow.serratest.modules.master.MasterRecycleViewModule

/**
 * @author mohammad rababah
 * desc general recycle view adapter to avoid duplication of adapters
 * @param activity an activity to use as context or ui events
 * @param arrayList the data that should be adapte on it
 * @param T module class that will hold drawing
 */
open class MasterRecycleViewAdapter<T: MasterRecycleViewModule>(open var activity: MasterActivity, open var arrayList: ArrayList<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return object : RecyclerView.ViewHolder(activity.layoutInflater.inflate(arrayList[0].rowLayouts[viewType], parent,false)) {}
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        arrayList[position].position = position
        arrayList[position].onBindViewHolder(activity,holder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        arrayList[position].loadViewType()
        return arrayList[position].viewTypes
    }


}

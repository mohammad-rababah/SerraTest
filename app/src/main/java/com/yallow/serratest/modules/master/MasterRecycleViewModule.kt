package com.yallow.serratest.modules.master


import androidx.recyclerview.widget.RecyclerView
import com.yallow.serratest.master.MasterActivity

/**
 * @author mohammad rababah
 * desc master recycle view module which responsible for any recycle view module
 * @sample Movie
 */
abstract class MasterRecycleViewModule : MasterModule() {

    abstract var rowLayouts: ArrayList<Int>

    abstract fun onBindViewHolder(masterActivity: MasterActivity, holder: RecyclerView.ViewHolder)
    var position = -1
    open var viewTypes = 0

    open fun  loadViewType(){}

}

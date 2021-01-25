package com.yallow.serratest.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yallow.serratest.R
import kotlinx.android.synthetic.main.loader_recycle_view.view.*

/**
 *  @author mohammad rababah
 * desc custom pager recycleView with progress
 */
open class SerraLoaderRecycleView(context: Context?, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    var recycleView: RecyclerView
    var layoutManager: RecyclerView.LayoutManager? = null
        set(value) {
            field = value
            recycleView.layoutManager = value
        }
    var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
        set(value) {
            field = value
            if (recycleView.layoutManager == null)
                recycleView.layoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            recycleView.adapter = value
        }

    init {
        View.inflate(context, R.layout.loader_recycle_view, this)
        recycleView = loaderRecycleView.loaderRecycleView
        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recycleView.layoutManager = layoutManager



    }

    fun showButtonProgress() {
     loaderRecycleViewBottomProgress.visibility = View.VISIBLE

    }

    fun hideButtonProgress() {
       loaderRecycleViewBottomProgress.visibility = View.GONE

    }


}
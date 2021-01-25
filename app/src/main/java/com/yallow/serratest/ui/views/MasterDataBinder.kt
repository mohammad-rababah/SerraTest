package com.yallow.serratest.ui.views

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter

object MasterDataBinder{


    /**
     *  @author mohammad rababah
     * desc special data bind data for loader image view
     */
    @JvmStatic
    @BindingAdapter("app:image_url")
    fun loadImagefromUrl(yallowImage : SerraImageView,imageUrl : String?) {
        yallowImage.imagePath = imageUrl
        yallowImage.loadImageFromUrl()
    }


}
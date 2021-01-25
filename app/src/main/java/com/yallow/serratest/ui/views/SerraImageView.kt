package com.yallow.serratest.ui.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener
import com.yallow.serratest.R


/**
 * Created by moham on 7/23/2017.
 *
 *
 * desc custom loader image view with caching configurations
 * */


open class SerraImageView(context: Context, attrs: AttributeSet?) :
    AppCompatImageView(context, attrs), ImageLoadingListener {


    @DrawableRes
    internal var defaultImage: Int = R.drawable.ic_launcher_foreground

    var imagePath: String? = null
    var originalBitMap: Bitmap? = null

    companion object {
        const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/w500/"

        /**
         *  @author mohammad rababah
         * desc two way chaching image (memory and cach)
         */
        fun configure(context: Context) {
            val options =
                DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).bitmapConfig(
                    Bitmap.Config.RGB_565
                ).build()
            val configs = ImageLoaderConfiguration.Builder(context)
                .diskCacheFileCount(1000).defaultDisplayImageOptions(options)
                .threadPriority(Thread.MAX_PRIORITY).memoryCache(
                    WeakMemoryCache()
                )
                .denyCacheImageMultipleSizesInMemory().threadPoolSize(5)
                .build()
            ImageLoader.getInstance().init(configs)
        }


    }


    init {
        if (attrs != null) {

            val attributes = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.SerraImageView,
                0, 0
            )

            try {

                imagePath = attributes.getString(R.styleable.SerraImageView_image_url)
            } finally {
                attributes.recycle()
            }
            if (!imagePath.isNullOrEmpty()) {
                loadImageFromUrl()
            }
            this.setImageResource(defaultImage)
        }

    }

    fun loadImageFromUrl(imagePath: String? = this.imagePath) {
        if (imagePath.isNullOrEmpty()) {
            setDefaultImage(defaultImage)
            return
        }
        val fullUrl = IMAGE_PREFIX + imagePath

        val imageLoader = ImageLoader.getInstance()
        imageLoader.displayImage(fullUrl, this, this)
    }



    fun setDefaultImage(defaultImage: Int) {
        this.defaultImage = defaultImage
        setImageResource(defaultImage)
    }

    fun loadDefaultImage() {
        setImageResource(defaultImage)
    }

    override fun onLoadingComplete(imageUri: String?, view: View?, loadedImage: Bitmap?) {
        this.originalBitMap = loadedImage

    }

    override fun onLoadingStarted(imageUri: String?, view: View?) {
    }

    override fun onLoadingCancelled(imageUri: String?, view: View?) {
    }

    override fun onLoadingFailed(imageUri: String?, view: View?, failReason: FailReason?) {

    }



}

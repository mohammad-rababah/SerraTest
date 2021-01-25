package com.yallow.serratest

import android.app.Application
import com.yallow.serratest.ui.views.SerraImageView
/**
 *  @author mohammad rababah
 * desc main application calls
 */
class SerraApplication : Application(){
    companion object{
        lateinit var serraApplication :SerraApplication
    }
    /**
     *  @author mohammad rababah
     * desc should configure image before any calls
     */
    override fun onCreate() {
        super.onCreate()
        serraApplication = this

        SerraImageView.configure(this)
    }
}
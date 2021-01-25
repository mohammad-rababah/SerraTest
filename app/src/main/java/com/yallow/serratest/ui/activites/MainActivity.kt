package com.yallow.serratest.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.yallow.serratest.R
import com.yallow.serratest.master.MasterActivity
import com.yallow.serratest.ui.fragments.MainFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * @author mohammad rababah
 * desc main and launcher activity with single fragment
 */


class MainActivity : MasterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivityRootContainer, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }



}
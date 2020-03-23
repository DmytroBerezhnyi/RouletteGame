package com.roulette.roulettegame.ui.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.LayoutRes
import moxy.MvpAppCompatActivity


abstract class MyMvpAppCompatActivity(@LayoutRes idRes : Int) :
    MvpAppCompatActivity(idRes) {

    val hideUiDelayMillis : Long = 2000

    var hideSysUiTime : Long = 0

    lateinit var handler: Handler

    private val runnable : Runnable by lazy { Runnable {
        val curTime = System.currentTimeMillis()

        val deltaTime = curTime - hideSysUiTime

        if(deltaTime >= hideUiDelayMillis) { runOnUiThread { hideSystemUI() }
        } else {
            handler.postDelayed(runnable, hideUiDelayMillis)
        }
    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        handler = Handler()

        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                hideSysUiTime = System.currentTimeMillis()
                handler.postDelayed(runnable, hideUiDelayMillis)
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }


    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private fun showSystemUI() {
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}

package com.roulette.roulettegame.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.roulette.roulettegame.R
import kotlinx.android.synthetic.main.activity_web.*


class WebActivity : AppCompatActivity(R.layout.activity_web) {

    private val homePageUrl = "https://www.fcase.ru/app.php"

    private val webViewState = "webViewState"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        if(savedInstanceState == null)
        webView.loadUrl(homePageUrl)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val webViewBundle = Bundle()
        webView.saveState(webViewBundle)

        outState.putBundle(webViewState, webViewBundle)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webView.restoreState(savedInstanceState.getBundle(webViewState))
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            finish()
        }
    }
}
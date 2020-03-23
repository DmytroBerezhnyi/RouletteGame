package com.roulette.roulettegame.ui.view

import android.content.Context
import android.util.AttributeSet
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class MyWebView(context: Context, attributeSet: AttributeSet) : WebView(context, attributeSet) {

    init {
        initWebView()
    }

    private fun initWebView() {
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.allowFileAccess = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.setSupportZoom(false)
            settings.builtInZoomControls = false
            settings.allowContentAccess = true
            settings.displayZoomControls = false
            settings.domStorageEnabled = true
            settings.defaultTextEncodingName = "utf-8"

            webChromeClient = WebChromeClient()

            webViewClient = MyWebViewClient()

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
        } else {
            CookieManager.getInstance().setAcceptCookie(true)
        }
    }

    private class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return false
        }
    }
}
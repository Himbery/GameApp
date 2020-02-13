package com.gamestudio.gametest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.web_activity.*
import android.webkit.WebViewClient as WebViewClient1

class WebActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_activity)

        val URL = intent.getStringExtra("url")

        progress_bar.visibility
        web_view.loadUrl(URL)

//        back_btn.setOnClickListener(this)
    }
//    internal var backPressed = false

    override fun onBackPressed(){
//        if (web_view.canGoBack()) {
//            web_view.goBack()
//        } else if (backPressed) {
            super.onBackPressed()
//        }
    }
//    override fun onClick(v: View?) {
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//    }
}
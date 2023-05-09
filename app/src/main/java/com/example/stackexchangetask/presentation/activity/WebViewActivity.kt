package com.example.stackexchangetask.presentation.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.stackexchangetask.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        var pageFirstTimeLoaded = true
        val webURL = intent.getStringExtra("WEB_URL") ?: "https://stackoverflow.com/questions/76204981/managing-a-long-running-api-listener-using-python#76204981"

        val customWebViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (!pageFirstTimeLoaded) binding.upperProgressBar.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (pageFirstTimeLoaded) {
                    pageFirstTimeLoaded = false
                    binding.progressBar.hide()
                } else binding.upperProgressBar.hide()
            }

        }

        binding.webview.apply {
            settings.javaScriptEnabled = true
            webViewClient = customWebViewClient
            loadUrl(webURL)
        }

        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.webview.canGoBack()) binding.webview.goBack()
        else finish()
    }
}
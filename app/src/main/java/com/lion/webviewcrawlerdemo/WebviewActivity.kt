package com.lion.webviewcrawlerdemo

import PermissionUtil
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.webkit.*
import com.lion.webviewcrawlerdemo.interfaces.PermissionListener
import com.lion.webviewcrawlerdemo.utils.APP_CACHE_DIRNAME
import com.lion.webviewcrawlerdemo.utils.LocLog
import com.lion.webviewcrawlerdemo.utils.TEST_URL
import com.yanzhenjie.permission.Permission
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        getPermissionForLog()
        initWebviewSettings()
        initWebview()
    }

    private fun getPermissionForLog() {
        PermissionUtil.permissionApply(this@WebviewActivity, Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE,
                listener = object : PermissionListener {
                    override fun onGranted(permissions: Array<out String>) {

                    }

                    override fun onDenied(permissions: Array<out String>, hint: String) {
                        Snackbar.make(webview.rootView,hint,Snackbar.LENGTH_LONG).show()
                    }

                })
    }

    private fun initWebview() {
        webview.webViewClient = object :WebViewClient(){
            /**
             *在开始加载网页时会回调
             */
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                url?.let { LocLog.action("onPageStarted: $url") }
            }

            /**
             * 在结束加载网页时会回调
             */
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                url?.let {
                    LocLog.action("onPageFinished: $url")
                    if (url.contains("wappass.baidu.com/passport/?login"))
                        webview.loadUrl("javascript:window.Android.getSource(" +
                                "document.getElementsByTagName('html')[0].innerHTML);")
                }
                //wappass.baidu.com/passport/?login
            }

            /**
             *拦截 url 跳转,在里边添加点击链接跳转或者操作
             */
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                request?.url.apply {
                    LocLog.action("""shouldOverrideUrlLoading: ${this.toString()}""")
                    view?.loadUrl(this.toString())
                }
                return true
            }

            /**
             *当接收到https错误时，会回调此函数，在其中可以做错误处理
             */
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }

            /**
             * 异步： 在每一次请求资源时，都会通过这个函数来回调
             * 注意返回值可以为空
             * wappass.baidu.com/wp/api/login?tt
             */
            override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                request?.url.apply {
                    LocLog.action("""shouldInterceptRequest: ${this}""")
                    if (!this.toString().contains("wappass.baidu.com/wp/api/login?tt"))
                        return super.shouldInterceptRequest(view, request)
                }

                webview.post {
                    webview.loadUrl("javascript:window.Android.getAccount(" +
                            "document.getElementsByTagName('input')[0].value);")
                    webview.loadUrl("javascript:window.Android.getPwd(" +
                            "document.getElementsByTagName('input')[1].value);")
                }
                return super.shouldInterceptRequest(view, request)
            }
        }

        webview.addJavascriptInterface(JSHook(), "Android")
        webview.loadUrl(TEST_URL)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebviewSettings() {
        //声明WebSettings子类
        val webSettings = webview.settings
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.javaScriptEnabled = true
        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
        webSettings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.displayZoomControls = false //隐藏原生的缩放控件
        //其他细节操作
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        webSettings.allowFileAccess = true //设置可以访问文件
        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = true //支持自动加载图片
        webSettings.defaultTextEncodingName = "utf-8"//设置编码格式

        webSettings.domStorageEnabled = true // 开启 DOM storage API 功能
        webSettings.databaseEnabled = true   //开启 database storage API 功能
        webSettings.setAppCacheEnabled(true)//开启 Application Caches 功能

        webSettings.setAppCachePath(APP_CACHE_DIRNAME) //设置  Application Caches 缓存目录
    }

    inner class JSHook{
        @JavascriptInterface
        fun getSource(html: String?) {
            html?.apply { LocLog.action(" getSource: $this") }
        }

        @JavascriptInterface
        fun getAccount(account: String?) {
            account?.apply {
                LocLog.action("getAccount:$this")
                tv_account?.setText(this)
            }
        }

        @JavascriptInterface
        fun getPwd(pwd: String?) {
            pwd?.apply {
                LocLog.action("getPwd: $this")
                tv_pwd?.setText(this)
            }
        }
    }
}

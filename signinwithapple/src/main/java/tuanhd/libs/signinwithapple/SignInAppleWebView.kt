package tuanhd.libs.signinwithapple

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebView

@SuppressLint("ViewConstructor", "SetJavaScriptEnabled")
class SignInAppleWebView (context: Context?, callbackScheme: String, callback: (String) -> Unit): WebView(context!!){
    init {
        settings.apply {
            javaScriptEnabled = true
            useWideViewPort = true
            javaScriptCanOpenWindowsAutomatically = true
        }

        webViewClient =
            SignInAppleWebClient(callbackScheme, callback)
    }
}
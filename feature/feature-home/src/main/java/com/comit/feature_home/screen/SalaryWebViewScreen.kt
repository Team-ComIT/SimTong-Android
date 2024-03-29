package com.comit.feature_home.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

private const val WebViewURL = "https://em.sungsimdang.co.kr:8443/"

@Composable
fun SalaryWebViewScreen() {
    val webViewState =
        rememberWebViewState(
            url = WebViewURL,
            additionalHttpHeaders = emptyMap()
        )

    WebView(
        state = webViewState,
        onCreated = { webView ->
            with(webView) {
                settings.run {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    javaScriptCanOpenWindowsAutomatically = false
                }
            }
        },
    )
}

@Preview
@Composable
fun PreviewHolidayWebViewScreen() {
    SalaryWebViewScreen()
}

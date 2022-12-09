package com.comit.feature_home.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

private const val WebViewURL = "https://em.sungsimdang.co.kr:8443/"

@Composable
fun HolidayWebViewScreen() {
    val webViewState =
        rememberWebViewState(
            url = WebViewURL,
            additionalHttpHeaders = emptyMap()
        )

    WebView(
        state = webViewState,
    )
}

@Preview
@Composable
fun PreviewHolidayWebViewScreen() {
    HolidayWebViewScreen()
}
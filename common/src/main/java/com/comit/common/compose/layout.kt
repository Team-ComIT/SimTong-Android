package com.comit.common.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val SimpleLayoutBottomPadding = PaddingValues(
    horizontal = 0.dp,
)

private val SimpleLayoutContentPadding = PaddingValues(
    horizontal = 20.dp,
)

private val DP_MAX = 1000.dp

@Composable
fun SimTongSimpleLayout(
    topAppBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
    bottomContent: @Composable () -> Unit,
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            topAppBar()

            Column(
                modifier = Modifier
                    .padding(SimpleLayoutContentPadding)
                    .verticalScroll(scrollState)
                    .heightIn(min = 0.dp, max = DP_MAX)
            ) {
                content()
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    SimpleLayoutBottomPadding
                )
        ) {
            bottomContent()
        }
    }
}
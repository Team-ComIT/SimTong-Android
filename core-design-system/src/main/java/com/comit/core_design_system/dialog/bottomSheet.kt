package com.comit.core_design_system.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.button.RedIconButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.typography.Title2
import kotlinx.coroutines.launch

private val BottomSheetShape = RoundedCornerShape(
    topStart = 16.dp,
    topEnd = 16.dp,
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SimBottomSheetDialog(
    useHandle: Boolean = false,
    sheetState: ModalBottomSheetState,
    sheetContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetShape = BottomSheetShape,
        sheetContent = {
            SimBottomSheetContent(
                useHandle = useHandle,
                sheetContent = sheetContent,
            )
        },
        sheetBackgroundColor = Color.Transparent,
        scrimColor = SimTongColor.Gray500,
    ) {
        content()
    }
}

@Composable
private fun SimBottomSheetContent(
    useHandle: Boolean,
    sheetContent: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SimTongColor.White,
            ),
    ) {
        SimBottomSheetHandle(
            useHandle = useHandle,
        )

        sheetContent()
    }
}

private val BottomSheetContentPadding = PaddingValues(
    vertical = 8.dp,
)

private val BottomSheetHandleSize = DpSize(
    width = 40.dp,
    height = 4.dp,
)

private val BottomSheetHandleShape = RoundedCornerShape(
    size = 2.dp,
)

@Composable
private fun SimBottomSheetHandle(
    useHandle: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BottomSheetContentPadding),
        contentAlignment = Alignment.Center,
    ) {
        if (useHandle) {
            Box(
                modifier = Modifier
                    .size(BottomSheetHandleSize)
                    .clip(BottomSheetHandleShape)
                    .background(
                        color = SimTongColor.Gray300,
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PreviewBottomSheetDialog() {

    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    SimBottomSheetDialog(
        useHandle = true,
        sheetState = bottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Title2(text = "안녕하세요!")

                Spacer(modifier = Modifier.height(24.dp))

                Body5(text = "테스트에요!")

                Spacer(modifier = Modifier.height(32.dp))

                BigRedRoundButton(text = "심통 사용하기") {
                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }
                }
            }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            RedIconButton(
                painter = painterResource(id = SimTongIcons.More),
                contentDescription = null
            ) {
                coroutineScope.launch {
                    bottomSheetState.show()
                }
            }
        }
    }
}

package com.comit.common.dialog.timerdialog

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.comit.common.utils.string
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.button.SimTongSmallRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.typography.Body3
import com.commit.common.R

@Stable
private val DialogSize: Dp = 330.dp

@Stable
private val DialogShape: Dp = 10.dp

@Stable
private val DialogItemPadding = PaddingValues(
    horizontal = 25.dp
)

@Composable
fun SimTongTimerDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    onDismissRequest: () -> Unit,
    alarm: String,
    onBtnClick: (String) -> Unit,
    properties: DialogProperties = DialogProperties(),
) {
    var hour by remember { mutableStateOf(0) }
    val hourList = ArrayList<Int>().map {
        for(i in 1 until 24) {

        }
    }

    Log.d("TAG", "SimTongTimerDialog: $hourList")

    if(visible) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties,
        ) {
            Column(
                modifier = modifier
                    .size(DialogSize)
                    .background(
                        color = SimTongColor.White,
                        shape = RoundedCornerShape(DialogShape),
                    )
            ) {
                Spacer(modifier = Modifier.height(17.dp))

                Body3(
                    text = stringResource(id = R.string.alarm),
                    modifier = Modifier.padding(DialogItemPadding),
                )

                Spacer(modifier = Modifier.height(19.dp))

//                ListItemPicker(
//                    value = string.format("%02d", hour),
//                    onValueChange = ,
//                    list =
//                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    SimTongSmallRoundButton(
                        text = stringResource(id = R.string.Cancel),
                        color = SimTongButtonColor.GRAY,
                        modifier = Modifier.width(170.dp),
                    ) {
                        onDismissRequest()
                    }

                    SimTongSmallRoundButton(
                        text = stringResource(id = R.string.Save),
                        color = SimTongButtonColor.RED,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .width(170.dp)
                    ) {
                        onDismissRequest()
                    }
                }
            }
        }
    }
}
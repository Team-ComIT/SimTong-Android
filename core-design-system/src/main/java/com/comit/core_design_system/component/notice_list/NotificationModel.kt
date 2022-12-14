package com.comit.core_design_system.component.notice_list

import androidx.compose.ui.Modifier
import com.comit.core_design_system.icon.SimTongIcon

data class NotificationModel(
    val modifier: Modifier = Modifier,
    val icon: SimTongIcon,
    val typeIcon: SimTongIcon,
    val text: String,
    val time: String
)

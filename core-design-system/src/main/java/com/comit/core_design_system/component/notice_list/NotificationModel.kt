package com.comit.core_design_system.component.notice_list

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

data class NotificationModel(
    val modifier: Modifier = Modifier,
    val icon: Painter,
    val typeIcon: Painter,
    val text: String,
    val time: String
)
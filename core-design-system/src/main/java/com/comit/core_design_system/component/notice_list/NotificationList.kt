package com.comit.core_design_system.component.notice_list

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.util.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val CARD_HEIGHT = 56
const val CARD_OFFSET = -56f

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoroutinesApi
@Composable
fun NotificationList(
    modifier: Modifier = Modifier,
    notifications: List<NotificationModel>,
    onDeleteItem: ((Int) -> Unit)? = null
) {
    val deletedItem = remember { mutableStateListOf<NotificationModel>() }

    LazyColumn {
        itemsIndexed(notifications) { index, item ->
            AnimatedVisibility(
                visible = !deletedItem.contains(item),
                enter = expandVertically(),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 1000))
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {

                    IconButton(
                        modifier = Modifier
                            .background(SimTongColor.MainColor)
                            .width(56.dp)
                            .fillMaxHeight(),
                        onClick = {
                            deletedItem.add(item)
                            if (onDeleteItem != null) {
                                onDeleteItem(index)
                            }
                        }
                    ) {
                        Image(
                            painter = painterResource(
                                id = SimTongIcon.Trash.drawableId,
                            ),
                            contentDescription = SimTongIcon.Trash.contentDescription,
                        )
                    }

                    var isRevealed by remember { mutableStateOf(false) }

                    DraggableNotification(
                        modifier = Modifier.fillMaxWidth(),
                        isRevealed = isRevealed,
                        cardHeight = CARD_HEIGHT.dp,
                        cardOffset = CARD_OFFSET.dp(),
                        onExpand = {
                            isRevealed = false
                        },
                        onCollapse = {
                            isRevealed = true
                        },
                        notificationModel = item
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Preview
@Composable
fun PreviewNotificationList() {
    var notifications by remember { mutableStateOf<List<NotificationModel>>(listOf()) }

    notifications = listOf(
        NotificationModel(
            icon = SimTongIcon.Image,
            typeIcon = SimTongIcon.More,
            text = "안녕하세요. 테스트입니다.",
            time = "방금 전"
        ),
        NotificationModel(
            icon = SimTongIcon.Image,
            typeIcon = SimTongIcon.More,
            text = "안녕하세요. 테스트입니다.",
            time = "1시간 전"
        ),
        NotificationModel(
            icon = SimTongIcon.Image,
            typeIcon = SimTongIcon.More,
            text = "안녕하세요. 테스트입니다.",
            time = "1시간 전"
        )
    )

    NotificationList(
        modifier = Modifier.padding(horizontal = 20.dp),
        notifications = notifications,
        onDeleteItem = { notifications.toMutableList().removeAt(index = it) }
    )
}

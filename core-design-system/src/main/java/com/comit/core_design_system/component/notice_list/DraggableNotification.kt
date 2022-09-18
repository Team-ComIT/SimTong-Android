package com.comit.core_design_system.component.notice_list

import android.annotation.SuppressLint
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.Body10
import com.comit.core_design_system.theme.Body9
import com.comit.core_design_system.theme.SimTongColor
import kotlin.math.roundToInt

const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableNotification(
    modifier: Modifier = Modifier,
    notificationModel: NotificationModel,
    cardHeight: Dp,
    isRevealed: Boolean,
    cardOffset: Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
) {
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    val transition = updateTransition(transitionState, "cardTransition")

    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) cardOffset else 0f },

    )

    Notification(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(cardHeight)
            .offset { IntOffset(offsetTransition.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount >= MIN_DRAG_AMOUNT -> onExpand()
                        dragAmount < -MIN_DRAG_AMOUNT -> onCollapse()
                    }
                }
            },
        notificationModel = notificationModel
    )
}

@Composable
fun Notification(
    modifier: Modifier = Modifier,
    backgroundColor: Color = SimTongColor.SkyBlue,
    newBackgroundColor: Color = SimTongColor.White,
    notificationModel: NotificationModel
) {
    val isNew = notificationModel.time == "방금 전"

    val backgroundColor = if (isNew) backgroundColor else newBackgroundColor

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .height(64.dp)
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(44.dp)) {
            Image(
                painter = notificationModel.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopStart)
            )

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        clip = true
                    )
                    .background(
                        color = SimTongColor.White,
                        shape = CircleShape
                    )
                    .align(
                        Alignment.BottomEnd
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = notificationModel.typeIcon,
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Body9(text = notificationModel.text)

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isNew) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(6.dp)
                            .background(SimTongColor.MainColor)
                    )

                    Spacer(modifier = Modifier.width(6.dp))
                }

                Body10(text = notificationModel.time)
            }
        }
    }
}

@Preview
@Composable
fun PreviewNotification() {
    Column {
        Notification(
            notificationModel = NotificationModel(
                icon = painterResource(id = SimTongIcons.Image),
                typeIcon = painterResource(id = SimTongIcons.More),
                text = "안녕하세요. 테스트입니다.",
                time = "1시간 전"
            )
        )

        Notification(
            notificationModel = NotificationModel(
                icon = painterResource(id = SimTongIcons.Image),
                typeIcon = painterResource(id = SimTongIcons.More),
                text = "안녕하세요. 테스트입니다.",
                time = "1시간 전"
            )
        )
    }
}

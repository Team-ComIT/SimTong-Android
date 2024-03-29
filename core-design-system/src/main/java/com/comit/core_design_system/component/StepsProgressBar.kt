package com.comit.core_design_system.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.color.SimTongColor
import kotlinx.coroutines.delay

@Stable
private val StepsProgressBarWeight: Float = 1F

@Stable
private val StepsProgressBarDelay: Long = 10

private const val StepsProgressFirstStep = 1

/**
 * Implement [StepsProgressBar], a Progress Bar in Step form.
 *
 * @param modifier [Modifier] to use to draw the StepsProgressBar
 * @param numberOfSteps number of step
 * @param currentStep current step
 */
@Composable
fun StepsProgressBar(
    modifier: Modifier = Modifier,
    numberOfSteps: Int,
    currentStep: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 1..numberOfSteps) {
            BasicStep(
                modifier = Modifier.weight(StepsProgressBarWeight),
                isCompete = step <= currentStep,
                isCurrent = step == currentStep,
                isFirst = step == StepsProgressFirstStep
            )
        }
    }
}

@Stable
private val BasicStepProgressSize: Float = 0.1f

@Stable
private val BasicStepProgressIncrease: Float = 0.01f

@Stable
private val BasicStepBigSize = 11.dp

@Stable
private val BasicStepSmallSize = 7.dp

@Stable
private val BasicStepLinearSize = 2.dp

@Composable
fun BasicStep(
    modifier: Modifier = Modifier,
    isCompete: Boolean,
    isCurrent: Boolean,
    isFirst: Boolean
) {

    val color = if (isCompete) SimTongColor.MainColor else SimTongColor.Gray400
    val viewSize = if (isCurrent) BasicStepBigSize else BasicStepSmallSize

    if (isFirst) {
        Box(
            modifier = Modifier
                .size(viewSize)
                .clip(shape = CircleShape)
                .background(color)
        )
    } else {
        Box(modifier = modifier) {

            var progress by remember { mutableStateOf(BasicStepProgressSize) }

            val animatedProgress by animateFloatAsState(
                targetValue = progress,
            )

            LaunchedEffect(true) {
                while ((progress < 1)) {
                    progress += BasicStepProgressIncrease
                    delay(StepsProgressBarDelay)
                }
            }

            if (isCurrent) {
                LinearProgressIndicator(
                    progress = animatedProgress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(BasicStepLinearSize)
                        .align(CenterStart),
                    color = color
                )
            } else {
                Divider(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .animateContentSize(),
                    color = color,
                    thickness = BasicStepLinearSize
                )
            }

            Box(
                modifier = Modifier
                    .size(viewSize)
                    .align(Alignment.CenterEnd)
                    .clip(shape = CircleShape)
                    .background(color)
            )
        }
    }
}

@Stable
private val StepsProgressBarPreviewMutableStateCurrentStep: Int = 3

@Preview
@Composable
fun StepsProgressBarPreview() {
    val currentStep = remember { mutableStateOf(StepsProgressBarPreviewMutableStateCurrentStep) }

    StepsProgressBar(
        modifier = Modifier.fillMaxWidth(),
        numberOfSteps = 5,
        currentStep = currentStep.value
    )
}

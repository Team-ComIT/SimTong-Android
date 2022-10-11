package com.comit.core_design_system.contans

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.dp

private object ShapeSize {

    @Stable
    val SMALL = 2.dp

    @Stable
    val MEDIUM = 5.dp

    @Stable
    val LARGE = 10.dp
}

@Stable
val RoundShapes = Shapes(
    small = RoundedCornerShape(ShapeSize.SMALL),
    medium = RoundedCornerShape(ShapeSize.MEDIUM),
    large = RoundedCornerShape(ShapeSize.LARGE)
)

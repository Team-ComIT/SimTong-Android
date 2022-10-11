@file:Suppress("TooManyFunctions")

package com.comit.core_design_system.typography

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.comit.core_design_system.R
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.modifier.simClickable

internal val notoSansFamily = FontFamily(
    Font(R.font.noto_sans_kr_black, FontWeight.Black),
    Font(R.font.noto_sans_kr_bold, FontWeight.Bold),
    Font(R.font.noto_sans_kr_medium, FontWeight.Medium),
    Font(R.font.noto_sans_kr_regular, FontWeight.Normal),
    Font(R.font.noto_sans_kr_light, FontWeight.Light),
    Font(R.font.noto_sans_kr_thin, FontWeight.Thin)
)

object SimTongTypography {

    @Stable
    val title1 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    )

    @Stable
    val title2 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
    )

    @Stable
    val title3 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    )

    @Stable
    val body1 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    )

    @Stable
    val body2 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    )

    @Stable
    val body3 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    )

    @Stable
    val body4 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    )

    @Stable
    val body5 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp
    )

    @Stable
    val body6 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    )

    @Stable
    val body7 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp
    )

    @Stable
    val body8 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )

    @Stable
    val body9 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

    @Stable
    val body10 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    )

    @Stable
    val body11 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 9.sp
    )

    @Stable
    val body12 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp
    )

    @Stable
    val body13 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp
    )

    @Stable
    val body14 = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
}

@Composable
fun Title1(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.title1,
        color = color,
    )
}

@Composable
fun Title2(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.title2,
        color = color,
    )
}

@Composable
fun Title3(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.title3,
        color = color,
    )
}

@Composable
fun Body1(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body1,
        color = color,
    )
}

@Composable
fun Body2(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body2,
        color = color,
    )
}

@Composable
fun Body3(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body3,
        color = color,
    )
}

@Composable
fun Body4(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body4,
        color = color,
    )
}

@Composable
fun Body5(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body5,
        color = color,
    )
}

@Composable
fun Body6(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body6,
        color = color,
    )
}

@Composable
fun Body7(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body7,
        color = color,
    )
}

@Composable
fun Body8(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body8,
        color = color,
    )
}

@Composable
fun Body9(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body9,
        color = color,
    )
}

@Composable
fun Body10(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body10,
        color = color,
    )
}

@Composable
fun Body11(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body11,
        color = color,
    )
}

@Composable
fun Body12(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body12,
        color = color,
    )
}

@Composable
fun Body13(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body13,
        color = color,
    )
}

@Composable
fun Body14(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Black,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body14,
        color = color,
    )
}

@Composable
fun Error(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SimTongColor.Error,
    rippleEnabled: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.simClickable(
            rippleEnabled = rippleEnabled,
            onClick = onClick
        ),
        text = text,
        style = SimTongTypography.body8,
        color = color,
    )
}
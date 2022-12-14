package com.comit.core_design_system.component

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body6

@Stable
private val HeaderHeight = 38.dp

@Stable
private val HeaderHorizontalPadding = PaddingValues(horizontal = 0.dp)

/**
 * Implement the [Header] of the SimTongDesignSystem.
 * Default form of Header component
 *
 * @param modifier [Modifier] to use to draw the SimTongTextField
 * @param beilState the state of beil
 * @param headerText text in Header
 * @param enabledBackBtn Whether the back button is enabled
 * @param enabledMoreBtn Whether the more button is enabled
 * @param enabledPlusBtn Whether the plus button is enabled
 * @param enabledBeilBtn Whether the beil button is enabled
 * @param enabledPeopleBtn Whether the people button is enabled
 * @param enabledTextBtn Whether the side text button is enabled
 * @param onPrevious Callback to be invoked when a back button is clicked
 * @param onMenu Callback to be invoked when a menu button is clicked
 * @param onPlus Callback to be invoked when a plus button is clicked
 * @param onBeil Callback to be invoked when a beil button is clicked
 * @param onMyPage Callback to be invoked when a MyPage button is clicked
 */
@Composable
fun Header(
    modifier: Modifier = Modifier,
    beilState: Boolean = false,
    headerText: String,
    sideBtnText: String? = null,
    enabledBackBtn: Boolean = false,
    enabledMoreBtn: Boolean = false,
    enabledPlusBtn: Boolean = false,
    enabledBeilBtn: Boolean = false,
    enabledPeopleBtn: Boolean = false,
    enabledTextBtn: Boolean = false,
    onPrevious: (() -> Unit)? = null,
    onMenu: (() -> Unit)? = null,
    onPlus: (() -> Unit)? = null,
    onBeil: (() -> Unit)? = null,
    onMyPage: (() -> Unit)? = null,
    onTextBtnClicked: (() -> Unit)? = null,
) {

    val textBtnColor = animateColorAsState(
        if (enabledTextBtn) SimTongColor.MainColor else SimTongColor.MainColor200
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(HeaderHeight)
            .padding(HeaderHorizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (enabledBackBtn) {
            IconButton(
                onClick = onPrevious ?: {},
                modifier = Modifier.size(21.dp),
            ) {
                Icon(
                    painter = painterResource(
                        id = SimTongIcon.Back_Small.drawableId,
                    ),
                    contentDescription = stringResource(
                        id = R.string.description_ic_back,
                    ),
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Body3(
            text = headerText,
        )

        Spacer(modifier = Modifier.width(8.dp))

        if (enabledMoreBtn) {
            IconButton(
                onClick = onMenu ?: {},
                modifier = Modifier.size(17.dp),
            ) {
                Icon(
                    painter = painterResource(
                        id = SimTongIcon.More.drawableId,
                    ),
                    contentDescription = stringResource(
                        id = R.string.description_ic_more,
                    ),
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        if (enabledPlusBtn) {
            IconButton(
                onClick = onPlus ?: {},
                modifier = Modifier.size(21.dp),
            ) {
                Icon(
                    painter = painterResource(
                        id = SimTongIcon.Plus.drawableId,
                    ),
                    contentDescription = stringResource(
                        id = R.string.description_ic_plus,
                    ),
                )
            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        if (enabledBeilBtn) {
            IconButton(
                onClick = onBeil ?: {},
                modifier = Modifier.size(21.dp),
            ) {
                Image(
                    painter = painterResource(
                        id = if (beilState) SimTongIcon.Beil_On.drawableId
                        else SimTongIcon.Beil_Off.drawableId,
                    ),
                    contentDescription = stringResource(
                        id = if (beilState) R.string.description_ic_beil_on
                        else R.string.description_ic_beil_off,
                    ),
                )
            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        if (enabledPeopleBtn) {
            IconButton(
                onClick = onMyPage ?: {},
                modifier = Modifier.size(21.dp),
            ) {
                Icon(
                    painter = painterResource(
                        id = SimTongIcon.MyPage.drawableId,
                    ),
                    contentDescription = stringResource(
                        id = R.string.description_ic_my_page,
                    ),
                )
            }
        }

        if (sideBtnText != null) {
            Body6(
                modifier = Modifier
                    .simClickable(
                        rippleEnabled = false,
                        runIf = enabledTextBtn,
                    ) {
                        if (onTextBtnClicked != null) {
                            onTextBtnClicked()
                        }
                    }
                    .padding(6.dp),
                text = stringResource(id = R.string.fix),
                color = textBtnColor.value,
            )
        }
    }
}

@Stable
private val BigHeaderHeight = 60.dp

@Stable
private val BigHeaderHorizontalPadding = PaddingValues(horizontal = 26.dp)

/**
 * Implement the [BigHeader] of the SimTongDesignSystem.
 * Large form of Header component
 *
 * @param modifier [Modifier] to use to draw the SimTongTextField
 * @param text text in BigHeader
 * @param onPrevious Callback to be invoked when a back button is clicked
 */
@Composable
fun BigHeader(
    modifier: Modifier = Modifier,
    text: String? = null,
    onPrevious: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(BigHeaderHeight)
            .padding(BigHeaderHorizontalPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onPrevious,
            modifier = Modifier
                .size(24.dp),
        ) {
            Icon(
                painter = painterResource(
                    id = SimTongIcon.Back_Big.drawableId,
                ),
                contentDescription = stringResource(
                    id = R.string.description_ic_back,
                )
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        if (text != null) {
            Body1(
                text = text,
            )
        }
    }
}

@Preview
@Composable
fun PreviewHeader() {
    val context = LocalContext.current

    Column {
        Header(headerText = "전체 지점", beilState = true, enabledBackBtn = true)

        Spacer(modifier = Modifier.height(16.dp))

        Header(
            headerText = "전체 지점",
            enabledBackBtn = true,
            enabledMoreBtn = true,
            enabledBeilBtn = true,
            enabledPeopleBtn = true,
            enabledPlusBtn = true,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Header(
            headerText = "전체 지점",
            sideBtnText = "수정",
            enabledTextBtn = true,
            enabledBackBtn = true,
            onTextBtnClicked = {
                Toast.makeText(context, "text btn clicked!", Toast.LENGTH_SHORT).show()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BigHeader(text = "회원가입") {
        }
    }
}

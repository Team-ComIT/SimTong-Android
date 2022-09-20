package com.comit.core_design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.Body1
import com.comit.core_design_system.theme.Body3

@Composable
fun Header(
    modifier: Modifier = Modifier,
    beilState: Boolean = false,
    headerText: String,
    enabledBackBtn: Boolean = true,
    enabledMoreBtn: Boolean = true,
    enabledPlusBtn: Boolean = true,
    enabledBeilBtn: Boolean = true,
    enabledPeopleBtn: Boolean = true,
    onPrevious: (() -> Unit)? = null,
    onMenu: (() -> Unit)? = null,
    onPlus: (() -> Unit)? = null,
    onBeil: (() -> Unit)? = null,
    onMyPage: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(38.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (enabledBackBtn) {
            IconButton(onClick = onPrevious ?: {}, modifier = Modifier.size(21.dp)) {
            Icon(
                painter = painterResource(id = SimTongIcons.Back(true)),
                contentDescription = stringResource(
                    id = R.string.description_ic_back
                )
            )
        }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Body3(text = headerText)

        Spacer(modifier = Modifier.width(8.dp))

        if (enabledMoreBtn) {
            IconButton(onClick = onMenu ?: {}, modifier = Modifier.size(17.dp)) {
            Icon(
                painter = painterResource(id = SimTongIcons.More),
                contentDescription = stringResource(id = R.string.description_ic_more)
            )
        }
        }

        Spacer(modifier = Modifier.weight(1f))

        if (enabledPlusBtn) {
            IconButton(onClick = onPlus ?: {}, modifier = Modifier.size(21.dp)) {
            Icon(
                painter = painterResource(id = SimTongIcons.Plus),
                contentDescription = stringResource(id = R.string.description_ic_plus)
            )
        }
        }

        Spacer(modifier = Modifier.width(20.dp))

        if (enabledBeilBtn) {
            IconButton(onClick = onBeil ?: {}, modifier = Modifier.size(21.dp)) {
            Image(
                painter = painterResource(id = SimTongIcons.Beil(beilState)),
                contentDescription = stringResource(
                    id = if (beilState) R.string.description_ic_beil_on
                    else R.string.description_ic_beil_off
                )
            )
        }
        }

        Spacer(modifier = Modifier.width(20.dp))

        if (enabledPeopleBtn) {
            IconButton(onClick = onMyPage ?: {}, modifier = Modifier.size(21.dp)) {
            Icon(
                painter = painterResource(id = SimTongIcons.MyPage),
                contentDescription = stringResource(
                    id = R.string.description_ic_my_page
                )
            )
        }
        }
    }
}

@Composable
fun BigHeader(
    modifier: Modifier = Modifier,
    text: String,
    onPrevious: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 26.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPrevious, modifier = Modifier.size(24.dp)) {
            Icon(
                painter = painterResource(id = SimTongIcons.Back(false)),
                contentDescription = stringResource(
                    id = R.string.description_ic_back
                )
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        Body1(text = text)
    }
}

@Preview
@Composable
fun PreviewHeader() {
    Column {
        Header(headerText = "전체 지점", beilState = true)

        Spacer(modifier = Modifier.height(16.dp))

        Header(headerText = "전체 지점", enabledBackBtn = false, enabledPlusBtn = false)

        Spacer(modifier = Modifier.height(16.dp))

        BigHeader(text = "회원가입") {
        }
    }
}

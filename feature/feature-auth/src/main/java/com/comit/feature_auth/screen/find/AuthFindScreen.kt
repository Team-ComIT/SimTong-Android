package com.comit.feature_auth.screen.find

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.Header
import com.comit.core_design_system.component.TabBar
import com.comit.core_design_system.dialog.SimBottomSheetDialog
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.theme.SimTongTheme
import com.comit.feature_auth.R
import com.comit.feature_auth.screen.find.findEmployeeNumber.FindEmployeeNumScreen
import com.comit.feature_auth.screen.find.findEmployeeNumber.FindPlaceLazyColumn
import com.comit.feature_auth.screen.find.password.FindPasswordScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.CoroutineScope

private val AuthFindScreenTabBarHeight = 35.dp

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun AuthFindScreen(
    navController: NavController,
) {
    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    SimBottomSheetDialog(
        sheetState = bottomSheetState,
        sheetContent = {
            FindPlaceLazyColumn(
                coroutineScope = coroutineScope,
                bottomSheetState = bottomSheetState
            )
        }
    ) {
        AuthFindScreenBasic(
            onPrevious = {
                navController.popBackStack()
            },
            coroutineScope = coroutineScope,
            bottomSheetState = bottomSheetState,
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun ChangeAuthScreen(
    index: Int,
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
) {
    when (index) {
        0 -> FindEmployeeNumScreen(
            coroutineScope = coroutineScope,
            bottomSheetState = bottomSheetState,
        )
        1 -> FindPasswordScreen()
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun AuthFindScreenBasic(
    onPrevious: () -> Unit,
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
) {
    Column {
        BigHeader {
            onPrevious()
        }

        Spacer(modifier = Modifier.height(31.dp))

        TabBar(
            modifier = Modifier
                .height(AuthFindScreenTabBarHeight),
            backgroundColor = MaterialTheme.colors.background,
            filters = listOf(
                stringResource(id = R.string.find_employee),
                stringResource(id = R.string.find_password)
            ),
            changeScreen = {
                ChangeAuthScreen(
                    index = it,
                    coroutineScope = coroutineScope,
                    bottomSheetState = bottomSheetState,
                )
            },
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun PreviewAuthFindScreen() {
    AuthFindScreen(
        navController = rememberNavController()
    )
}

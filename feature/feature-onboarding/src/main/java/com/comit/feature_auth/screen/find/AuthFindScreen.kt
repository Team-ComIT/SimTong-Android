package com.comit.feature_auth.screen.find

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.TabBar
import com.comit.feature_auth.R
import com.comit.feature_auth.screen.find.employeeNumber.FindEmployeeNumMainScreen
import com.comit.feature_auth.screen.find.password.FindPasswordScreen
import com.google.accompanist.pager.ExperimentalPagerApi

private val AuthFindScreenTabBarHeight = 35.dp

private const val FindEmployeeNumberScreen = 0
private const val FindPasswordScreen = 1

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun AuthFindScreen(
    navController: NavController,
) {
    Column {
        var index by remember { mutableStateOf(0) }

        BigHeader {
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(10.dp))

        TabBar(
            modifier = Modifier
                .height(AuthFindScreenTabBarHeight),
            backgroundColor = Color.Transparent,
            filters = listOf(
                stringResource(id = R.string.find_employee),
                stringResource(id = R.string.find_password)
            ),
            changeScreen = { changedPageIndex ->
                index = changedPageIndex

                Crossfade(targetState = index) { pageState ->
                    when (pageState) {
                        FindEmployeeNumberScreen -> FindEmployeeNumMainScreen(
                            navController = navController,
                        )
                        FindPasswordScreen -> FindPasswordScreen(
                            navController = navController,
                        )
                    }
                }
            },
        )
    }
}

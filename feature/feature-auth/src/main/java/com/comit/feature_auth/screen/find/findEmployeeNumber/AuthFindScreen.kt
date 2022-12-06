package com.comit.feature_auth.screen.find.findEmployeeNumber

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.TabBar
import com.comit.feature_auth.R
import com.google.accompanist.pager.ExperimentalPagerApi

private val AuthFindScreenTabBarHeight = 35.dp

private const val FindEmployeeNumberScreen = 0
private const val FindPasswordScreen = 1
private const val FindEmployeeResultScreen = 2

//TODO(limsaehyun - 더미 데이터 교체 필요)
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun AuthFindScreen(
    navController: NavController,
) {

    AuthFindScreenBasic(
        onPrevious = {
            navController.popBackStack()
        },
    )
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun AuthFindScreenBasic(
    onPrevious: () -> Unit,
) {
    Column {

        var index by remember { mutableStateOf(0) }
        var number by remember { mutableStateOf("") }

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
            changeScreen = { changedPageIndex ->
                index = changedPageIndex

                Crossfade(targetState = index) { pageState ->
                    when (pageState) {
                        FindEmployeeNumberScreen -> FindEmployeeNumScreen(
                            navigateToResultScreen = {
                                number = it
                            }
                        )
                        FindPasswordScreen -> FindPasswordScreen()
                        FindEmployeeResultScreen -> FindEmployeeNumResultScreen(
                            name = "asd",
                            employeeNumber = number,
                            onPrevious = onPrevious,
                        )
                    }
                }
            },
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun PreviewAuthFindScreen() {
}

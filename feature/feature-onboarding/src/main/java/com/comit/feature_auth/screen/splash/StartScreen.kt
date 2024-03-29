@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.comit.feature_auth.screen.splash

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.feature_auth.R
import com.comit.feature_auth.mvi.StartSideEffect
import com.comit.feature_auth.vm.StartViewModel
import com.comit.navigator.SimTongScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

private val imageList = listOf(
    R.drawable.bg_splash_page1,
    R.drawable.bg_splash_page2,
    R.drawable.bg_splash_page3,
    R.drawable.bg_splash_page4,
    R.drawable.bg_splash_page5,
    R.drawable.bg_splash_page6,
)

const val Delay: Long = 3000

const val Tween: Int = 600

@Suppress("MagicNumber") // 화면의 미세한 offset 을 조정하기 위함
@OptIn(ExperimentalPagerApi::class, InternalCoroutinesApi::class)
@Composable
fun StartScreen(
    navController: NavController,
) {
    val vm: StartViewModel = hiltViewModel()
    val container = vm.container
    val sideEffect = container.sideEffectFlow

    val pagerState = rememberPagerState(
        pageCount = imageList.size,
    )

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(Delay)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(Tween)
            )
        }
    }

    LaunchedEffect(key1 = vm) {
        vm.authLogin()
    }

    sideEffect.observeWithLifecycle {
        when (it) {
            StartSideEffect.NavigateToHome -> {
                navController.navigate(
                    route = SimTongScreen.Home.MAIN,
                ) {
                    popUpTo(0)
                }
            }
            StartSideEffect.NavigateToSignIn -> {
                navController.navigate(
                    route = SimTongScreen.Auth.SIGN_IN,
                ) {
                    popUpTo(0)
                }
            }
        }
    }

    Box {
        HorizontalPager(
            state = pagerState,
        ) { page ->
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(
                    id = imageList[page]
                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(74.dp))

            Image(
                painter = painterResource(id = R.drawable.bg_splash_title),
                contentDescription = null,
                modifier = Modifier.size(119.dp, 24.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_splash_simtong),
                    contentDescription = null,
                    modifier = Modifier.size(108.dp, 53.dp)
                )
                Image(
                    modifier = Modifier
                        .size(66.dp)
                        .offset(x = (-8).dp),
                    painter = painterResource(id = SimTongIcon.Logo.drawableId),
                    contentDescription = null,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            SimTongBigRoundButton(
                text = stringResource(id = R.string.sign_up),
            ) {
                navController.navigate(SimTongScreen.Auth.SIGN_UP)
            }

            Spacer(modifier = Modifier.height(15.dp))

            SimTongBigRoundButton(
                text = stringResource(id = R.string.sign_in),
                color = SimTongButtonColor.WHITE,
            ) {
                navController.navigate(SimTongScreen.Auth.SIGN_IN)
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

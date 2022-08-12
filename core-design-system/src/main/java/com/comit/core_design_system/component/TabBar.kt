package com.comit.core_design_system.component

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.comit.core_design_system.theme.Body5
import com.comit.core_design_system.theme.OtherColor
import com.comit.core_design_system.theme.SimTongColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun TabBar(modifier: Modifier = Modifier, filters: List<String>, onClick: (Int) -> Unit) {

    val pagerState = rememberPagerState(filters.size)
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = SimTongColor.White,
        contentColor = SimTongColor.MainColor,
        modifier = modifier
    ) {
        filters.forEachIndexed { index, filter ->

            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                    onClick(index)
                },
                text = { Body5(text = filter) },

                selectedContentColor = OtherColor.Black34,
                unselectedContentColor = OtherColor.GrayB3,
            )
        }
    }
}

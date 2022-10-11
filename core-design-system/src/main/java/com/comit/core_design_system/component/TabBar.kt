
package com.comit.core_design_system.component

import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.typography.Body5
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

data class TabItem(
    val title: String,
    //val screen: Unit
    )

@ExperimentalPagerApi
@Composable
fun TabBar(
    modifier: Modifier = Modifier,
    filters: List<TabItem>,
    onClick: (Int) -> Unit = {},
    backgroundColor: Color = SimTongColor.White,
    contentColor: Color = SimTongColor.MainColor,
    selectedContentColor: Color = SimTongColor.OtherColor.Black34,
    unselectedContentColor: Color = SimTongColor.OtherColor.GrayB3
) {

    val pagerState = rememberPagerState(filters.size)
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
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
                text = { Body5(text = filter.title) },

                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor
            )


        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewTabBar() {
    TabBar(
        modifier = Modifier
            .height(35.dp),
        filters = listOf()
    )
}

package com.comit.feature_home.contract

import com.comit.core_design_system.component.Meal

data class HomeState(

    val mealList: List<Meal> = emptyList(),
)

sealed class HomeSideEffect {
    object CanWriteHoliday : HomeSideEffect()

    object TokenException : HomeSideEffect()

    object CannotWriteHoliday : HomeSideEffect()
}

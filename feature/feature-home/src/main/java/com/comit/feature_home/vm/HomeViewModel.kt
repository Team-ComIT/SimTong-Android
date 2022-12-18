package com.comit.feature_home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.core_design_system.component.Meal
import com.comit.domain.exception.UnknownException
import com.comit.domain.model.MealEntity
import com.comit.domain.usecase.menu.FetchMenuUseCase
import com.comit.feature_home.contract.HomeSideEffect
import com.comit.feature_home.contract.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMenuUseCase: FetchMenuUseCase,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container = container<HomeState, HomeSideEffect>(HomeState())

    fun fetchMenu(
        startAt: String,
        endAt: String,
    ) = intent {
        viewModelScope.launch {
            fetchMenuUseCase(
                startAt = startAt,
                endAt = endAt,
            ).onSuccess { meals ->
                reduce { state.copy(mealList = meals.map { it.toDesignSystemModel() }) }
            }.onFailure {
                throw UnknownException(it.message)
            }
        }
    }

    private fun MealEntity.toDesignSystemModel() =
        Meal(
            date = date,
            food = food,
        )
}

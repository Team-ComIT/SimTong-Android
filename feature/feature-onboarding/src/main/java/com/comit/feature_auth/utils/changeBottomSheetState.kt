package com.comit.feature_auth.utils

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
internal fun changeBottomSheetState(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetType: BottomSheetType,
) {
    coroutineScope.launch {
        when (bottomSheetType) {
            BottomSheetType.Hide -> bottomSheetState.hide()
            BottomSheetType.Show -> bottomSheetState.show()
        }
    }
}

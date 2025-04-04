package com.bursur.spiragps.route.conditions

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bursur.spiragps.route.data.Condition

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ConditionalSheet(conditions: ArrayList<Condition>, conditionState: ConditionState) {
    val sheetState = rememberModalBottomSheetState()

    if(conditionState.sheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { conditionState.sheetOpen = false },
            //windowInsets = WindowInsets.navigationBars
        ) {
            FlowRow(
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                conditions.forEach {
                    ConditionalView(it, conditionState)
                }
            }
        }
    }
}
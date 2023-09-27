package com.chocolate.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.chocolate.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageOptionsBottomSheet(
    onSaveMessage: () -> Unit,
    onDismiss: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column() {
            BottomSheetItem(
                icon = painterResource(id = R.drawable.bookmark),
                text = stringResource(R.string.add_to_saved_items),
                onClickItem = {
                    onSaveMessage()
                    onDismiss()
                }
            )
        }
    }
}
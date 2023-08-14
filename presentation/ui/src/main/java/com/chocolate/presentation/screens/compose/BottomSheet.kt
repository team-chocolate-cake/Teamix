package com.chocolate.presentation.screens.compose

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.Space16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    colors: CustomColorsPalette,
    onDismissBottomSheet: () -> Unit,
    content:  @Composable (ColumnScope.() -> Unit)
) {
    val modelBottomSheetScaffoldState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { onDismissBottomSheet() },
        sheetState = modelBottomSheetScaffoldState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        shape = RoundedCornerShape(topStart = Space16, topEnd = Space16),
        contentColor = colors.card,
        modifier = Modifier.wrapContentSize()
    ) {
        content()
    }
}
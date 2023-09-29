package com.chocolate.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentOptionsBottomSheet(
    onSave: () -> Unit,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
    ) {
        Column(modifier = Modifier.padding(bottom = 24.dp)) {
            BottomSheetItem(
                icon = painterResource(id = R.drawable.bookmark),
                text = stringResource(R.string.add_to_saved_items),
                onClickItem = {
                    onSave()
                    onDismiss()
                }
            )
        }
    }
}
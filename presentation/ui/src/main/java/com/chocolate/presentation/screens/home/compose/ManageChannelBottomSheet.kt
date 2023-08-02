package com.chocolate.presentation.screens.home.compose

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CustomColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageChannelBottomSheet(colors: CustomColorsPalette, onDismiss: () -> Unit) {
    val modelBottomSheetScaffoldState = rememberModalBottomSheetState()
    var openDialog by remember { mutableStateOf(false) }
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modelBottomSheetScaffoldState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        contentColor = colors.card
    ) {
        if (openDialog) {
            ManageChannelAlertDialog(
                title = "leave",
                subTitle = "a",
                colors,
                onDismiss = { openDialog = false })
        }
        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            thickness = 4.dp,
            color = colors.onBackground38
        )
        ItemManageChannelBottomSheet(
            colors,
            title = "Mute Channel",
            painter = painterResource(id = R.drawable.ic_mute_channel)
        ) { openDialog = true }
        ItemManageChannelBottomSheet(
            colors,
            title = "Star Channel",
            painter = painterResource(id = R.drawable.ic_star)
        ) { openDialog = true }
        ItemManageChannelBottomSheet(
            colors,
            title = "Copy Link",
            painter = painterResource(id = R.drawable.ic_copy)
        ) { }
        ItemManageChannelBottomSheet(
            colors,
            title = "Copy Name",
            painter = painterResource(id = R.drawable.ic_copy)
        ) { }
        ItemManageChannelBottomSheet(
            colors,
            title = "Leave Channel",
            painter = painterResource(id = R.drawable.ic_leave),
            itemColor = colors.red60
        ) { openDialog = true }
    }
}
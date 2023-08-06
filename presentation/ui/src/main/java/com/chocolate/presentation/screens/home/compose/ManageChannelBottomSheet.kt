package com.chocolate.presentation.screens.home.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
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
import androidx.compose.ui.res.stringResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.Space16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageChannelBottomSheet(colors: CustomColorsPalette, onDismissBottomSheet: () -> Unit) {
    val modelBottomSheetScaffoldState = rememberModalBottomSheetState()
    var openDialog by remember { mutableStateOf(false) }
    var openDialogMute by remember { mutableStateOf(false) }
    var openDialogStar by remember { mutableStateOf(false) }
    var openDialogLeave by remember { mutableStateOf(false) }
    ModalBottomSheet(
        onDismissRequest = { onDismissBottomSheet() },
        sheetState = modelBottomSheetScaffoldState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        shape = RoundedCornerShape(topStart = Space16, topEnd = Space16),
        contentColor = colors.card,
        modifier = Modifier.wrapContentSize()
    ) {
        if (openDialog) {
            ManageChannelAlertDialog(
                title = when {
                    openDialogMute -> stringResource(R.string.mute)
                    openDialogStar -> stringResource(R.string.star)
                    openDialogLeave -> stringResource(R.string.leave)
                    else -> stringResource(R.string.unknown)
                },
                    subTitle = "a",
                    colors,
                    onDismiss = {
                        openDialog = false
                        openDialogMute = false
                        openDialogStar = false
                        openDialogLeave= false
                        onDismissBottomSheet()
                    })
        }
        CustomDivider(modifier = Modifier
            .align(Alignment.CenterHorizontally),
            thickness = 4,
            color = colors.onBackground38
            )
        ItemManageChannelBottomSheet(
            colors,
            title = stringResource(R.string.mute_channel),
            painter = painterResource(id = R.drawable.ic_mute_channel)
        ) {
            openDialog = true
            openDialogMute = true
        }
        CustomDivider(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = Space16)
        )
        ItemManageChannelBottomSheet(
            colors,
            title = stringResource(R.string.star_channel),
            painter = painterResource(id = R.drawable.ic_star)
        ) {
            openDialog = true
            openDialogStar = true
        }
        CustomDivider(modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = Space16))
        ItemManageChannelBottomSheet(
            colors,
            title = stringResource(R.string.copy_link),
            painter = painterResource(id = R.drawable.ic_copy)
        ) { }
        CustomDivider(modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = Space16))
        ItemManageChannelBottomSheet(
            colors,
            title = stringResource(R.string.copy_name),
            painter = painterResource(id = R.drawable.ic_copy)
        ) { }
        CustomDivider(modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = Space16))
        ItemManageChannelBottomSheet(
            colors,
            title = stringResource(R.string.leave_channel),
            painter = painterResource(id = R.drawable.ic_leave),
            itemColor = colors.red60,
        ) {
            openDialog = true
            openDialogLeave = true
        }
    }
}
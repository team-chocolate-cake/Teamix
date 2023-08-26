package com.chocolate.presentation.screens.home.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.BottomSheet
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.Space16

@Composable
fun ManageChannelBottomSheet(onDismissBottomSheet: () -> Unit, colors: CustomColorsPalette) {
    BottomSheet(colors = colors, onDismissBottomSheet = { onDismissBottomSheet() }) {
        var openDialog by remember { mutableStateOf(false) }
        var openDialogLeave by remember { mutableStateOf(false) }
        if (openDialog) {
            ManageChannelAlertDialog(
                title = when {
                    openDialogLeave -> stringResource(R.string.leave)
                    else -> stringResource(R.string.unknown)
                },
                subTitle = when {
                    openDialogLeave -> stringResource(R.string.title_subtitle)
                    else -> stringResource(R.string.na)
                },
                colors,
                onDismiss = {
                    openDialog = false
                    openDialogLeave = false
                    onDismissBottomSheet()
                })
        }
        Divider(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            thickness = 4.dp,
            color = colors.onBackground38
        )
        ItemManageChannelBottomSheet(
            colors,
            title = stringResource(R.string.mute_channel),
            painter = painterResource(id = R.drawable.ic_mute_channel)
        ) {
            openDialog = true
        }
        Divider(
            modifier = Modifier.wrapContentSize().padding(horizontal = Space16),
            color = colors.border
        )
        ItemManageChannelBottomSheet(
            colors,
            title = stringResource(R.string.star_channel),
            painter = painterResource(id = R.drawable.ic_star)
        ) {
            openDialog = true
        }
        Divider(
            modifier = Modifier.wrapContentSize().padding(horizontal = Space16),
            color = colors.border
        )
        ItemManageChannelBottomSheet(
            colors,
            title = stringResource(R.string.copy_link),
            painter = painterResource(id = R.drawable.ic_copy)
        ) { }
        Divider(
            modifier = Modifier.wrapContentSize().padding(horizontal = Space16),
            color = colors.border
        )
        ItemManageChannelBottomSheet(
            colors,
            title = stringResource(R.string.copy_name),
            painter = painterResource(id = R.drawable.ic_copy)
        ) { }
        Divider(
            modifier = Modifier.wrapContentSize().padding(horizontal = Space16),
            color = colors.border
        )
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
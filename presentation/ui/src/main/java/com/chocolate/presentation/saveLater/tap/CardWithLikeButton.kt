package com.chocolate.presentation.saveLater.tap

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space24
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageChannelBottomSheet(
    colors: CustomColorsPalette,
    onDismiss: () -> Unit,
    onArchiveClicked: () -> Unit,
    moveToInProgress: () ->Unit,
    removeFromLater: () -> Unit,
    savedItem: SavedItemUiState?,
    viewModel: SaveLaterViewModel
) {
    val modelBottomSheetScaffoldState = rememberModalBottomSheetState()
    ModalBottomSheet(

        onDismissRequest = { onDismiss() },
        sheetState = modelBottomSheetScaffoldState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        shape = RoundedCornerShape(topStart = Space16, topEnd = Space16),
        contentColor = colors.card,
        modifier = Modifier
            .wrapContentSize()
    ) {

        Divider(
            modifier = Modifier
                .width(50.dp)
                .align(Alignment.CenterHorizontally),
            thickness = Space4,
            color = colors.onBackground38
        )
        ItemManageChannelBottomSheet(
            colors,
            title = "Archive",
            painter = painterResource(id = R.drawable.archive),
            onClickItemManageChannelBottomSheet = { onArchiveClicked() }
        )
        ItemManageChannelBottomSheet(
            colors,
            title = "Move to in Progress",
            painter = painterResource(id = R.drawable.bookmark),
            onClickItemManageChannelBottomSheet = {moveToInProgress() }
        )
        ItemManageChannelBottomSheet(
            colors,
            title = "Remove from later",
            painter = painterResource(id = R.drawable.trash_bin_minimalistic),
            onClickItemManageChannelBottomSheet = { removeFromLater() }
        )
    }
}

@Composable
fun ItemManageChannelBottomSheet(
    colors: CustomColorsPalette,
    title: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    itemColor: Color = colors.onBackground60,
    onClickItemManageChannelBottomSheet: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Space16)
            .clickable { onClickItemManageChannelBottomSheet() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = title,
            tint = itemColor,
            modifier = Modifier.padding(end = Space8)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = itemColor
        )
    }
}

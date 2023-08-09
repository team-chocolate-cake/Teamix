package com.chocolate.presentation.saveLater

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chocolate.presentation.R
import com.chocolate.presentation.saveLater.tap.ManageChannelBottomSheet
import com.chocolate.presentation.saveLater.tap.SaveLaterViewModel
import com.chocolate.presentation.saveLater.tap.SavedItemOfDay
import com.chocolate.presentation.saveLater.tap.SavedItemState
import com.chocolate.presentation.saveLater.tap.SavedItemUiState
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.OnLightBackground
import com.chocolate.presentation.theme.OnLightOnBackground60
import com.chocolate.presentation.theme.OnLightPrimary
import com.chocolate.presentation.theme.OnPrimary
import com.jetpackcompose.tablayout.TabLayoutScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SaveLaterScreen(
) {
    TabLayoutScreen()
}

@Composable
fun SaveLaterScreenContent(savedLaterUISate: List<SavedItemOfDay>, viewModel: SaveLaterViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OnLightBackground)
    ) {
        if (savedLaterUISate.isNotEmpty())
            SavedItemsList(savedLaterUISate, viewModel)
        else {
            EmptyScreen()
        }
    }

}

@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_ll15l91p))
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "No Saved Items",
            modifier = Modifier.padding(start = 24.dp, end = 24.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Your saved items will appear here for easy\naccess and reference",
            modifier = Modifier.padding(start = 24.dp, end = 24.dp),
            textAlign = TextAlign.Center
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedItemsList(savedItems: List<SavedItemOfDay>, viewModel: SaveLaterViewModel) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        savedItems.forEach { (date, items) ->
            stickyHeader {
                DateHeader(date = date)
            }
            items(items) { savedItem ->
                SavedItemRow(savedItem = savedItem, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun DateHeader(date: String) {

    Row(
        Modifier
            .fillMaxWidth()
            .background(OnLightBackground)

    ) {
        Spacer(
            modifier = Modifier
                .width(16.dp)
                .height(16.dp)
        )
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = date, fontSize = 16.sp
        )
    }
}

@Composable
fun SavedItemRow(savedItem: SavedItemUiState, viewModel: SaveLaterViewModel) {
    val isBottomSheetShowing = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
            .clickable {
                isBottomSheetShowing.value = true

                viewModel.onCardClicked(savedItem)
            },
        colors = CardDefaults.cardColors(containerColor = OnPrimary)

    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(savedItem.userImageUrl),
                contentDescription = null,

                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)

            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp)
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = savedItem.name,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Image(
                        painter = painterResource(id = R.drawable.frame_48096465__1_),
                        contentDescription = null,
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(

                        text = savedItem.date, fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = savedItem.title,
                    fontSize = 12.sp,
                )

                Text(
                    text = savedItem.description,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Spacer(modifier = Modifier.weight(1f))
                when (savedItem.state) {
                    SavedItemState.IN_PROGRESS -> InProgressButtons {
                        viewModel.completeItem(
                            savedItem
                        )
                    }

                    SavedItemState.ARCHIVED -> ArchivedButton { viewModel.unarchiveItem(savedItem) }
                    SavedItemState.COMPLETED -> {
                        if (isBottomSheetShowing.value) {
                            ManageChannelBottomSheet(
                                colors = CustomColorsPalette(),
                                onDismiss = { isBottomSheetShowing.value = false },
                             
                                onArchiveClicked = { viewModel.moveToArchive(savedItem) },
                                moveToInProgress = { viewModel.moveToInProgress(savedItem) },
                                removeFromLater = { viewModel.removeFromCompleted(savedItem) }
                            )
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun ArchivedButton(
    onUnArchive: () -> Unit
) {
    Button(
        onClick = { onUnArchive() },
        colors =  ButtonDefaults.outlinedButtonColors(

        ),
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 16.dp)
            .border(3.dp, OnLightOnBackground60, shape = RoundedCornerShape(32.dp))

    ) {
        Icon(
            painter = painterResource(id = R.drawable.archive),
            contentDescription = null,
            modifier = Modifier.padding(end = 10.dp )
        )
        Text(text = "Un Archive", fontSize = 12.sp)
    }
}


@Composable
private fun InProgressButtons(onComplete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = onComplete,
            colors = ButtonDefaults.buttonColors(containerColor = OnLightPrimary),
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 16.dp, end = 16.dp)
        ) {
            Text(text = "Complete", fontSize = 12.sp)
        }

        Button(
            onClick = onComplete,
            colors = ButtonDefaults.buttonColors(containerColor = OnLightPrimary),
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_calendar_month_24),
                contentDescription = null,
                modifier = Modifier
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun saveLaterScreenPreview() {
    SaveLaterScreen()
}

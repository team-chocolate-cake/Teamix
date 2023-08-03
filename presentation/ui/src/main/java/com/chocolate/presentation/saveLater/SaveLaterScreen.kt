package com.chocolate.presentation.saveLater

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.saveLater.tap.SaveLaterViewModel
import com.chocolate.presentation.saveLater.tap.SavedItemOfDay
import com.chocolate.presentation.saveLater.tap.SavedItemState
import com.chocolate.presentation.saveLater.tap.SavedItemUiState
import com.chocolate.presentation.theme.OnLightBackground
import com.chocolate.presentation.theme.OnLightPrimary
import com.chocolate.presentation.theme.OnPrimary
import com.jetpackcompose.tablayout.TabLayoutScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SaveLaterScreen(
)  {
    TabLayoutScreen()
}

@Composable
fun SaveLaterScreenContent(savedLaterUISate:List<SavedItemOfDay>){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OnLightBackground)
    ) {
        SavedItemsList(savedLaterUISate)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedItemsList(savedItems: List<SavedItemOfDay>) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        savedItems.forEach { (date, items) ->
            stickyHeader {
                DateHeader(date = date)
            }
            items(items) { savedItem ->
                SavedItemRow(savedItem = savedItem)
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
        Spacer(modifier = Modifier
            .width(16.dp)
            .height(16.dp))
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = date, fontSize = 16.sp
        )
    }
}

@Composable
fun SavedItemRow(savedItem: SavedItemUiState) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(170.dp)
            .width(328.dp)
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp),
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
                when(savedItem.state){
                    SavedItemState.IN_PROGRESS ->  InProgressButtons()
                    SavedItemState.ARCHIVED -> ArchivedButton()
                    SavedItemState.COMPLETED ->{}
                }
            }
        }
    }

}

@Composable
fun ArchivedButton() {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = OnLightPrimary),
        modifier = Modifier
            .size(width = 124.dp, height = 32.dp)
    ) {
        Text(text = "Un Archive", fontSize = 12.sp)
    }
}

@Composable
private fun InProgressButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = OnLightPrimary),
            modifier = Modifier
                .size(width = 124.dp, height = 32.dp)
        ) {
            Text(text = "Complete", fontSize = 12.sp)
        }

        Icon(
            painter = painterResource(id = R.drawable.baseline_access_time_24),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .padding(start = 16.dp)
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun saveLaterScreenPreview() {
    SaveLaterScreen()
}

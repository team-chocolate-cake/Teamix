package com.chocolate.presentation.screens.add_task.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Border2
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.addTask.MembersOrganizationUiState


@Composable
fun MembersOrganization(
    state: List<MembersOrganizationUiState>,
    onclickAssignTask: (idMember: Int) -> Unit
) {
    val colors = MaterialTheme.customColors()
    val textStyle = MaterialTheme.typography

    Column {
        Text(
            modifier = Modifier.padding(bottom = SpacingXMedium),
            text = stringResource(id = R.string.assign_for),
            style = textStyle.labelMedium,
            color = colors.onBackground87,
            textAlign = TextAlign.Start
        )
        LazyRow() {
            items(state.size) { index ->
                val colorBorder = if (state[index].isSelected) colors.primary else colors.background
                Image(
                    painter = rememberAsyncImagePainter(model = state[index].imageUrl),
                    contentDescription = stringResource(R.string.image_stream),
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)

                        .border(width = Border2, colorBorder, shape = CircleShape)
                        .clickable {
                            Log.v("Ameerxzu", "clickable ${state[index].id}")

                            onclickAssignTask(state[index].id)
                        },
                    contentScale = ContentScale.FillBounds
                )
                Text(text = state[index].isSelected.toString())
            }
        }
    }

}
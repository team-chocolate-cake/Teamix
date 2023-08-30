package com.chocolate.presentation.screens.organizationTasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.theme.customColors

@Composable
fun TaskDetailsScreen(){
    TaskDetailsContent()
}

@Composable
fun TaskDetailsContent() {
    val color=MaterialTheme.customColors()
    val fontStyle = MaterialTheme.typography

    TeamixScaffold(
        isDarkMode = isSystemInDarkTheme(),
        hasAppBar = true,
        hasBackArrow = true,
        containerColorAppBar = color.card,
        title = "Task Details",
    ) {
        Column(   Modifier
            .fillMaxWidth()) {
            Box(
                Modifier.wrapContentSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp, bottom = 8.dp)
            ) {
                Row {
                    Icon(
                        modifier = Modifier.padding(end = 4.dp),
                        painter = painterResource(id = R.drawable.checklist_minimalistic),
                        contentDescription = null,
                        tint = color.onBackground87
                    )
                    Text(
                        text ="state.taskName",
                        color = color.onBackground87,
                        style = fontStyle.titleMedium
                    )
                }
                Text(
                   text = " state.taskDate", modifier = Modifier.align(Alignment.CenterEnd),
                    color = color.onBackground60, style = fontStyle.labelSmall
                )

            }

            Text(text = "sdsds")
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = stringResource(R.string.image_stream),
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(32.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun TaskDetailsPreview(){
    TaskDetailsScreen()
}
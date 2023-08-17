package com.chocolate.presentation.screens.home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CustomColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamixTopAppBar(imageUrl: String, title: String, colors: CustomColorsPalette) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
            /*    Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = stringResource(R.string.image_stream),
                    modifier = Modifier.padding(end = 12.dp).size(32.dp).clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )*/
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = colors.onPrimary,
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(colors.primary),
    )
}
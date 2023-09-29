package com.chocolate.presentation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingXXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamixAppBar(
    title: String,
    titleColor: Color,
    hasBackArrow: Boolean,
    modifier: Modifier = Modifier,
    hasImageUrl: Boolean,
    actions: @Composable() (RowScope.() -> Unit),
    imageUrl: String,
    contentDescription: String = "",
    containerColor: Color,
) {
    val colors = MaterialTheme.customColors()
    val navController = LocalNavController.current
    TopAppBar(
        modifier = modifier,
        actions = { actions() },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(visible = hasImageUrl) {
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = stringResource(R.string.image_stream),
                        modifier = Modifier
                            .padding(end = SpacingXXMedium)
                            .size(SpacingExtraHuge)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Text(
                    text = title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    color = titleColor,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            AnimatedVisibility(visible = hasBackArrow) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.alt_arrow_left),
                        tint = colors.onBackground87,
                        contentDescription = contentDescription
                    )
                }
            }

        },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = containerColor)
    )
}
package com.chocolate.presentation.screens.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.chocolate.presentation.theme.ImageSize110
import com.chocolate.presentation.theme.ImageSize130
import com.chocolate.presentation.theme.ImageSize158
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.profile.ProfileUiState

@Composable
fun ProfileImage(state: ProfileUiState) {
    val context = LocalContext.current
    val color = MaterialTheme.customColors()
    Box(Modifier.height(ImageSize158)) {
        Box(
            modifier = Modifier
                .padding(top = SpacingExtraHuge)
                .size(ImageSize130)
                .clip(CircleShape)
                .border(2.dp, color.primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            val imageRequest = ImageRequest.Builder(context)
                .data(state.imageUrl)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .transformations(CircleCropTransformation())
                .build()
            val imageLoader =
                ImageLoader.Builder(context).respectCacheHeaders(false).build()

            Image(
                modifier = Modifier
                    .size(ImageSize110)
                    .clip(CircleShape),
                painter = rememberAsyncImagePainter(imageRequest, imageLoader),
                contentDescription = null
            )
        }
    }

}

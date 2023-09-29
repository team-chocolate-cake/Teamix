package com.chocolate.presentation.screens.profile.composable

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.IconSize24
import com.chocolate.presentation.theme.IconSize30
import com.chocolate.presentation.theme.ImageSize110
import com.chocolate.presentation.theme.ImageSize130
import com.chocolate.presentation.theme.ImageSize158
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingSmall
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.profile.ProfileUiState

@Composable
fun ProfileImage(state: ProfileUiState, onImageChangeClick: (newUri: Uri) -> Unit) {
    val context = LocalContext.current
    val color = MaterialTheme.customColors()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onImageChangeClick(it)
        }
    }

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
        IconButton(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier
                .size(IconSize30)
                .clip(CircleShape)
                .align(Alignment.BottomEnd),
            colors = IconButtonDefaults.iconButtonColors(color.primary)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.camera),
                contentDescription = null,
                modifier = Modifier
                    .size(IconSize24)
                    .padding(bottom = SpacingSmall),
                tint = color.onPrimary
            )
        }
    }

}

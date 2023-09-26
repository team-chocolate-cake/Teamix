package com.chocolate.presentation.composable

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Border2
import com.chocolate.presentation.theme.IconSize24
import com.chocolate.presentation.theme.IconSize30
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingSmall
import com.chocolate.presentation.theme.customColors

@Composable
fun TeamixImagePicker(
    onImageSelected: (Uri) -> Unit,
    modifier: Modifier = Modifier,
    placeHolderImage: Int = R.drawable.default_user_image
) {
    // Created by Bilal
    val colors = MaterialTheme.customColors()
    val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = rememberAsyncImagePainter(
        imageUri.value.ifEmpty { placeHolderImage }
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onImageSelected(it)
            imageUri.value = it.toString()
        }
    }
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Box(
                modifier = Modifier
                    .padding(top = SpacingExtraHuge)
                    .size(130.dp)
                    .clip(CircleShape)
                    .border(Border2, colors.primary, CircleShape),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape),
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

            IconButton(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier
                    .size(IconSize30)
                    .clip(CircleShape)
                    .align(Alignment.BottomEnd),
                colors = IconButtonDefaults.iconButtonColors(colors.primary)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = null,
                    modifier = Modifier
                        .size(IconSize24)
                        .padding(bottom = SpacingSmall),
                    tint = colors.onPrimary
                )
            }
        }
    }
}
package com.chocolate.presentation.screens.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.IconSize24
import com.chocolate.presentation.theme.IconSize56
import com.chocolate.presentation.theme.ImageSize56
import com.chocolate.presentation.theme.Space12
import com.chocolate.presentation.theme.Space16

@Composable
fun OrganizationImageSheet(onClick:()->Unit,color: CustomColorsPalette){
    ProfileBottomSheet(
        colors = color,
        onDismissBottomSheet = { onClick() }) {
        Column(modifier = Modifier.padding(start = Space16, bottom =Space16)) {
            Text(
                text = stringResource(R.string.select_organization_image),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier,
                color = color.onBackground87
            )
            LazyRow(modifier = Modifier.padding(vertical = Space16)) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(end = Space16)
                            .size(IconSize56)
                            .clip(RoundedCornerShape(Space12))
                            .background(color.primary)
                            .clickable { }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(IconSize24),
                            tint = color.onPrimary
                        )
                    }

                }

                item {
                    Image(
                        modifier = Modifier
                            .size(ImageSize56)
                            .clip(RoundedCornerShape(12.dp)),
                        painter = painterResource(id = R.drawable.deb),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
package com.chocolate.presentation.profile.component

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
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CustomColorsPalette

@Composable
fun OrganizationImageSheet(onClick:()->Unit,color: CustomColorsPalette){
    ProfileBottomSheet(
        colors = color,
        onDismissBottomSheet = { onClick() }) {
        Column(modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)) {
            Text(
                text = "Select Organization Image",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier,
                color = color.onBackground87
            )
            LazyRow(modifier = Modifier.padding(vertical = 16.dp)) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(56.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(color.primary)
                            .clickable { }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(24.dp),
                            tint = color.onPrimary
                        )
                    }

                }

                item {
                    Image(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        painter = painterResource(id = R.drawable.deb),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
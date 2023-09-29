package com.chocolate.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.Float12
import com.chocolate.presentation.theme.SpacingHuge
import com.chocolate.presentation.theme.SpacingXXMedium

@Composable
fun TeamixButton(
    onClick: () -> Unit,
    modifier: Modifier=Modifier,
    enabled: Boolean = true,
    colors: CustomColorsPalette,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        color = if (enabled) colors.primary else colors.onSecondary38,
        shape = RoundedCornerShape(Float12)
    ) {
        Row(
            Modifier
                .defaultMinSize(
                    minWidth = ButtonDefaults.MinWidth,
                    minHeight = ButtonDefaults.MinHeight
                )
                .padding(
                    PaddingValues(
                        vertical = SpacingXXMedium,
                        horizontal = SpacingHuge
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}
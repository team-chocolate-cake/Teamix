package com.chocolate.presentation.screens.combosable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.IconSize16
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.TextFieldHeight48
import com.chocolate.presentation.theme.customColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.search)
) {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        shape = RoundedCornerShape(Radius12),
        modifier = modifier
            .fillMaxWidth().height(TextFieldHeight48),
        textStyle = MaterialTheme.typography.bodySmall,
        singleLine = true,
        value = text,
        onValueChange = { text = it },
        placeholder = { Text(text = hint, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.customColors().onBackground38) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(IconSize16)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.customColors().card,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )

    )
}

@Preview
@Composable
private fun SearchBoxPrev() {
    SearchBox()
}
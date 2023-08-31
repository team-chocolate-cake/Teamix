package com.chocolate.presentation.screens.profile.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CardHeight56
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.SpacingLarge
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.Thickness2
import com.chocolate.viewmodel.main.MainViewModel
import com.chocolate.viewmodel.profile.ProfileInteraction
import kotlinx.coroutines.launch

@Composable
fun ProfileSettingsPage(
    color: CustomColorsPalette,
    darkThemeState: Boolean,
    profileInteraction: ProfileInteraction
) {
    val coroutineScope = rememberCoroutineScope()

    val context= LocalContext.current

    LazyColumn(contentPadding = PaddingValues(all = SpacingXLarge)) {
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = SpacingXMedium)
                    .clip(RoundedCornerShape(SpacingLarge))
                    .wrapContentHeight()
                    .background(color.card)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            coroutineScope.launch {
                                profileInteraction.onClickDarkThemeSwitch(darkThemeState,context)
                            }
                        },
                    colors = CardDefaults.cardColors(color.card)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(CardHeight56)
                            .padding(horizontal = SpacingXLarge),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.dark_mode_icon),
                            contentDescription = null,
                            modifier = Modifier.padding(end = SpacingXMedium),
                            tint = color.onBackground60
                        )
                        Text(
                            text = stringResource(R.string.dark_theme),
                            style = MaterialTheme.typography.bodyMedium,
                            color = color.onBackground60
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(
                            checked = darkThemeState, onCheckedChange = {
                                coroutineScope.launch {
                                    profileInteraction.onClickDarkThemeSwitch(darkThemeState,context)
                                }

                            },
                            thumbContent = {
                                Icon(
                                    painter = painterResource(id = R.drawable.check_24px),
                                    contentDescription = null,
                                    tint = color.white,
                                )
                            }, colors = SwitchDefaults.colors(
                                checkedThumbColor = color.primary,
                                checkedIconColor = color.red,
                                checkedBorderColor = color.card,
                                uncheckedBorderColor = color.card,
                                checkedTrackColor = color.onSecondary38,
                                uncheckedTrackColor = color.gray
                            )
                        )
                    }
                }
            }
            Divider(color = color.background, thickness = Thickness2)
            SettingCard(
                click = { profileInteraction.onUpdateLanguageDialogState(true) },
                text = stringResource(R.string.language),
                icon = painterResource(id = R.drawable.language)
            )
            Divider(color = color.background, thickness = Thickness2)
            SettingCard(
                click = { profileInteraction.onUpdateLogoutDialogState(true) },
                text = stringResource(R.string.log_out),
                icon = painterResource(id = R.drawable.logout),
                iconColor = color.red60,
                textColor = color.red60
            )
        }
    }
}


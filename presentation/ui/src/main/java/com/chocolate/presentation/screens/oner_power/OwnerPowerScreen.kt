package com.chocolate.presentation.screens.oner_power

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.profile.composable.ChannelNameSheet
import com.chocolate.presentation.screens.profile.composable.MultiChoiceDialog
import com.chocolate.presentation.screens.profile.composable.OrganizationImageSheet
import com.chocolate.presentation.screens.profile.composable.OrganizationNameSheet
import com.chocolate.presentation.screens.profile.composable.SettingCard
import com.chocolate.presentation.theme.Space12
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.Space80
import com.chocolate.presentation.theme.Thickness2
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.profile.OwnerPowerInteraction
import com.chocolate.viewmodel.profile.OwnerPowerUiState
import com.chocolate.viewmodel.profile.OwnerPowerViewModel

@Composable
fun OwnerPowerScreen(
    navController: NavController,
    viewModel: OwnerPowerViewModel= hiltViewModel()){
    val state by viewModel.state.collectAsState()

    OwnerPowerContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerPowerContent(
    state: OwnerPowerUiState,
    ownerPowerInteraction: OwnerPowerInteraction
    ){
    val color = MaterialTheme.customColors()


    if (state.showChangeMemberRoleDialog) {
        MultiChoiceDialog(
            onDismissRequest = { ownerPowerInteraction.updateChangeMemberRoleDialogState(false)},
            whenChoice = {},
            choices = listOf(stringResource(R.string.guest),
                stringResource(R.string.member), stringResource(R.string.administrator),
                stringResource(
                    R.string.owner
                )
            ),
            oldSelectedChoice = ""
        )
    }
    if (state.showOrganizationNameSheet) {
        OrganizationNameSheet(onClick = { ownerPowerInteraction.updateOrganizationNameSheetState(false) }, color = color)
    }
    if (state.showCreateChannelSheet) {
        ChannelNameSheet(onClick = { ownerPowerInteraction.updateCreateChannelSheetState(false) }, color = color)
    }
    if (state.showOrganizationImageSheet) {
        OrganizationImageSheet(onClick = { ownerPowerInteraction.updateOrganizationImageState(false) }, color = color)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.owner_powers),
                    style = MaterialTheme.typography.titleMedium,
                    color = color.onBackground87
                )
            }, navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.alt_arrow_left),
                    contentDescription = null
                )
            }, colors = TopAppBarDefaults.topAppBarColors(color.onPrimary))
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Space16)
        ) {
            item {
                Text(
                    text = stringResource(R.string.organization), style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = Space80)
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = Space8)
                        .clip(RoundedCornerShape(Space12))
                        .wrapContentHeight()
                        .background(color.card)
                ) {
                    Column {
                        SettingCard(
                            click = { //navigate to members screen
                                    },
                            text = stringResource(R.string.invites_user),
                            icon = painterResource(id = R.drawable.invitesuser)
                        )
                        Divider(color = color.background, thickness = Thickness2)
                        SettingCard(
                            click = { ownerPowerInteraction.updateOrganizationNameSheetState(true) },
                            text = stringResource(R.string.edit_organization_name),
                            icon = painterResource(id = R.drawable.editorganizationname)
                        )
                        Divider(color = color.background, thickness = Thickness2)
                        SettingCard(
                            click = { ownerPowerInteraction.updateOrganizationNameSheetState(true) },
                            text = stringResource(R.string.edit_organization_image),
                            icon = painterResource(id = R.drawable.organizationimage)
                        )
                    }
                }
            }
            item {
                Text(
                    text = stringResource(R.string.permissions_roles), style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = Space16)
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = Space8)
                        .clip(RoundedCornerShape(Space12))
                        .wrapContentHeight()
                        .background(color.card)
                ) {
                    Column {
                        SettingCard(
                            click = { ownerPowerInteraction.updateChangeMemberRoleDialogState(true) },
                            text = stringResource(R.string.change_member_role),
                            icon = painterResource(id = R.drawable.ownerpowers)
                        )
                        Divider(color = color.background, thickness = Thickness2)
                        SettingCard(
                            click = { //navigate to members screen
                                    },
                            text = stringResource(R.string.who_invites_users),
                            icon = painterResource(id = R.drawable.whoinviteusers)
                        )
                        Divider(color = color.background, thickness = Thickness2)
                        SettingCard(
                            click = { //navigate to members screen
                            },
                            text = stringResource(R.string.who_can_add_custom_emoji),
                            icon = painterResource(id = R.drawable.userheart)
                        )
                        Divider(color = color.background, thickness = Thickness2)
                        SettingCard(
                            click = {//navigate to members screen
                                 },
                            text = stringResource(R.string.who_can_use_all_everyone_mentions),
                            icon = painterResource(id = R.drawable.mentions)
                        )
                    }
                }
            }
            item {
                Text(
                    text = stringResource(R.string.channel), style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = Space16)
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = Space8)
                        .clip(RoundedCornerShape(Space12))
                        .wrapContentHeight()
                        .background(color.card)
                ) {
                    Column {
                        SettingCard(
                            click = { ownerPowerInteraction.updateCreateChannelSheetState(true) },
                            text = stringResource(R.string.create_channel),
                            icon = painterResource(id = R.drawable.channel)
                        )
                        Divider(color = color.background, thickness = Thickness2)
                        SettingCard(
                            click = {
                                //navigate to channels  screen
                            },
                            text = stringResource(R.string.delete_channel),
                            icon = painterResource(id = R.drawable.deletechannel),
                            textColor = color.red
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OwnerPowerScreenPreview() {
    OwnerPowerScreen(rememberNavController())
}




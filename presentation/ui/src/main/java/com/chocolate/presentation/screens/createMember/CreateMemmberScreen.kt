package com.chocolate.presentation.screens.createMember

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.composable.TeamixImagePicker
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.create_channel.composable.ActionSnakeBar
import com.chocolate.presentation.screens.home.navigateToHome
import com.chocolate.presentation.theme.Border2
import com.chocolate.presentation.theme.IconSize24
import com.chocolate.presentation.theme.IconSize30
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingSmall
import com.chocolate.presentation.theme.SpacingUltraGigantic
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.SpacingXXLarge
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.createmember.CreateMemberInteraction
import com.chocolate.viewmodel.createmember.CreateMemberUiEffect
import com.chocolate.viewmodel.createmember.CreateMemberUiState
import com.chocolate.viewmodel.createmember.CreateMemberViewModel

@Composable
fun CreateMemberScreen(
    viewModel: CreateMemberViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current

    CollectUiEffect(viewModel.effect) { effect ->
        when (effect) {
            CreateMemberUiEffect.ShowImagePicker -> {}
            CreateMemberUiEffect.NavigateToLogin -> {
                navController.popBackStack()
            }
            CreateMemberUiEffect.NavigateToHome -> navController.navigateToHome()
        }
    }

    CreateMemberContent(
        interaction = viewModel,
        state = state
    )
}

@Composable
private fun CreateMemberContent(
    interaction: CreateMemberInteraction,
    state: CreateMemberUiState
) {
    val colors = MaterialTheme.customColors()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background),
        contentPadding = PaddingValues(SpacingXLarge),
        verticalArrangement = Arrangement.spacedBy(SpacingXXLarge),
    ) {
        item { TeamixImagePicker(interaction::onPersonalImageChange) }
        item { UserInformationInputsSection(interaction = interaction, state = state) }
        item {
            TeamixButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SpacingUltraGigantic),
                onClick = { interaction.onCreateAccountClick() },
                colors = colors,
            ) {
                AnimatedVisibility(visible = state.isLoading) {
                    CircularProgressIndicator(
                        color = colors.card,
                        modifier = Modifier
                            .size(SpacingXXLarge)
                            .align(Alignment.CenterVertically)
                    )
                }
                AnimatedVisibility(visible = !state.isLoading) {
                    Text(
                        text = stringResource(R.string.create_account),
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors.onPrimary
                    )
                }
            }
        }
        item {
            TextWithClickableAction(
                text = stringResource(R.string.do_you_have_account),
                clickableText = stringResource(id = R.string.sign_in),
                onTextClick = { interaction.onSignInClick() }
            )
        }
    }
    ActionSnakeBar(
        contentMessage = state.error.toString(),
        isVisible = state.error != null,
        isToggleButtonVisible = false
    )


}

@Composable
private fun UserInformationInputsSection(
    interaction: CreateMemberInteraction,
    state: CreateMemberUiState
) {
    InputFieldWithLabel(
        label = stringResource(id = R.string.full_name),
        onInputChange = { interaction.onFullNameChange(it) },
        inputFieldValue = state.fullName
    )

    InputFieldWithLabel(
        label = stringResource(id = R.string.email),
        onInputChange = { interaction.onEmailChange(it) },
        inputFieldValue = state.email
    )

    InputFieldWithLabel(
        label = stringResource(id = R.string.password),
        onInputChange = { interaction.onPasswordChange(it) },
        inputFieldValue = state.password,
        isPasswordStyle = true,
        onClickPasswordVisibility = { interaction.onPasswordVisibilityChange(it) },
        passwordVisibility = state.passwordVisibility
    )

    InputFieldWithLabel(
        label = stringResource(id = R.string.confirm_password),
        onInputChange = { interaction.onConfirmPasswordChange(it) },
        inputFieldValue = state.confirmPassword,
        isPasswordStyle = true,
        onClickPasswordVisibility = { interaction.onConfirmPasswordVisibilityChange(it) },
        passwordVisibility = state.confirmPasswordVisibility
    )
}

@Composable
private fun InputFieldWithLabel(
    label: String,
    inputFieldValue: String,
    onInputChange: (input: String) -> Unit,
    onClickPasswordVisibility: (isVisible: Boolean) -> Unit = {},
    isPasswordStyle: Boolean = false,
    passwordVisibility: Boolean = false,
) {
    val colors = MaterialTheme.customColors()
    val passwordIcon = if (passwordVisibility) R.drawable.ic_eye else R.drawable.ic_eye_closed

    Text(
        modifier = Modifier.padding(top = SpacingXLarge),
        text = label,
        style = MaterialTheme.typography.labelMedium,
        color = colors.onBackground87
    )

    if (isPasswordStyle) {
        TeamixTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SpacingXMedium),
            value = inputFieldValue,
            onValueChange = { onInputChange(it) },
            trailingIcon = {
                IconButton(onClick = { onClickPasswordVisibility(!passwordVisibility) }) {
                    Icon(painter = painterResource(id = passwordIcon), contentDescription = null)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        )
    } else {
        TeamixTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SpacingXMedium),
            value = inputFieldValue,
            onValueChange = { onInputChange(it) },
        )
    }

}

@Composable
private fun TextWithClickableAction(
    modifier: Modifier = Modifier,
    text: String,
    onTextClick: () -> Unit,
    clickableText: String
) {
    val colors = MaterialTheme.customColors()

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = colors.onBackground60,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .clickable(onClick = { onTextClick() })
                .padding(start = SpacingSmall),
            text = clickableText,
            color = colors.primary,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}




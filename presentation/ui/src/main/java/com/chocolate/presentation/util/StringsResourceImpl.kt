package com.chocolate.presentation.util

import android.content.Context
import com.chocolate.presentation.R
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringsResourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : StringsResource {
    override val emptyEmailMessage: String = getString(R.string.email_can_t_be_empty)

    override val emptyFullNameMessage: String = getString(R.string.full_name_can_t_be_empty)

    override val sameUserDataMessage: String =
        getString(R.string.user_information_can_t_be_the_same)

    override val noConnectionMessage: String = getString(R.string.no_internet_connection)

    override val globalMessageError: String = getString(R.string.globalMessageError)

    override val successMessage: String = getString(R.string.success)

    override val enterValidEmailAddress: String =
        getString(R.string.enter_a_valid_email_address)

    override val invalidEmailOrPassword: String = getString(R.string.invalid_email_or_password)

    override val organizationNameNotFound: String = getString(R.string.organization_name_not_found)

    override val organizationNameCannotBeEmpty: String =
        getString(R.string.organization_name_cannot_be_empty)

    override val channelNameValidation: String =
        getString(R.string.channel_name_validation)

    override val nullOrEmptyNewLanguage: String =
        getString(R.string.an_error_occurred_due_to_null_or_empty_new_language_string_value)

    override val failedSaveSelectedLanguage: String =
        getString(R.string.failed_to_save_the_selected_language_settings)
    
    override val theSameData: String = getString(R.string.the_same_data)
    
    override val failedEmailWhenEmpty: String =
        getString(R.string.the_email_shouldn_t_be_empty)
    
    override val failedFullNameWhenEmpty: String =
        getString(R.string.the_full_name_shouldn_t_be_empty)

    private fun getString(@androidx.annotation.StringRes stringsRes: Int): String {
        return context.getString(stringsRes)
    }
}
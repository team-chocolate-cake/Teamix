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
    override val emptyPassword: String
        get() = getString(R.string.password_cannot_be_empty)

    override val sameUserDataMessage: String =
        getString(R.string.user_information_can_t_be_the_same)

    override val noConnectionMessage: String = getString(R.string.no_internet_connection)

    override val globalMessageError: String = getString(R.string.globalMessageError)

    override val successMessage: String = getString(R.string.success)
    override val organizationNameAlreadyExist: String = getString(R.string.organization_name_already_exist)
    override val invalidImage: String = getString(R.string.invalid_image_uri)
    override val organizationNameIsSoLongException: String = getString(R.string.organization_name_is_long)

    override val enterValidEmailAddress: String =
        getString(R.string.enter_a_valid_email_address)

    override val invalidEmailOrPassword: String = getString(R.string.invalid_email_or_password)
    override val invalidChannelName: String
        get() = getString(R.string.invalid_channel_name)
    override val channelAlreadyExist: String
        get() = getString(R.string.channel_Already_Exist)

    override val cancel: String = getString(R.string.cancel)

    override val createChannel: String = getString(R.string.create_channel)

    override val organizationNameNotFound: String = getString(R.string.organization_name_not_found)

    override val organizationNameCannotBeEmpty: String =
        getString(R.string.organization_name_cannot_be_empty)

    override val organizationNameOrImageCannotBeEmpty: String=
        getString(R.string.organization_name_or_image_cannot_be_empty)

    override val channelNameValidation: String =
        getString(R.string.channel_name_validation)

    override val invalidTopicName: String=  getString(R.string.topic_name_validation)

    override val nullOrEmptyNewLanguage: String =
        getString(R.string.an_error_occurred_due_to_null_or_empty_new_language_string_value)

    override val failedSaveSelectedLanguage: String =
        getString(R.string.failed_to_save_the_selected_language_settings)

    override val theSameData: String = getString(R.string.the_same_data)

    override val failedEmailWhenEmpty: String =
        getString(R.string.the_email_shouldn_t_be_empty)

    override val failedFullNameWhenEmpty: String =
        getString(R.string.the_full_name_shouldn_t_be_empty)
    override val messageDeletedSuccessfully: String =
        getString(R.string.message_deleted_successfully)

    override val invalidUsername: String = getString(R.string.invites_username)
    override val invalidEmail: String = getString(R.string.invalid_email)
    override val passwordMismatch: String = getString(R.string.password_mismatch)
    override val memberAlreadyExist: String = getString(R.string.member_already_exist)
    override val allFieldsAreRequired: String = getString(R.string.all_field_required)
    override val savedForLater:String = getString(R.string.saved_for_later)
    override val topicDeletedSuccessfully :String = getString(R.string.topic_deleted_successfully)

    private fun getString(@androidx.annotation.StringRes stringsRes: Int): String {
        return context.getString(stringsRes)
    }
}
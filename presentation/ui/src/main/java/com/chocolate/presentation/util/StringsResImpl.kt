package com.chocolate.presentation.util

import android.content.Context
import com.chocolate.presentation.R
import com.chocolate.viewmodel.base.StringsRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringsResImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : StringsRes {

    override val emptyEmailMessage: String = getString(R.string.email_can_t_be_empty)

    override val emptyFullNameMessage: String = getString(R.string.full_name_can_t_be_empty)

    override val sameUserDataMessage: String =
        getString(R.string.user_information_can_t_be_the_same)

    override val noConnectionMessage: String = getString(R.string.no_internet_connection)
    override val globalMessageError: String = getString(R.string.globalMessageError)
    override val successMessage: String = getString(R.string.success)

    private fun getString(@androidx.annotation.StringRes stringsRes: Int): String {
        return context.getString(stringsRes)
    }
}
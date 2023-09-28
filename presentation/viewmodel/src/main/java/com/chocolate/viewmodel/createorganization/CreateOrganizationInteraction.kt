package com.chocolate.viewmodel.createorganization

import android.net.Uri

interface CreateOrganizationInteraction {
    fun onOrganizationNameChange(organizationName: String)
    fun onClickHaveOrganization()
    fun onClickNextButton()
    fun onSnackBarDismissed()
    fun onOrganizationImageChange(imageUri: Uri)
}
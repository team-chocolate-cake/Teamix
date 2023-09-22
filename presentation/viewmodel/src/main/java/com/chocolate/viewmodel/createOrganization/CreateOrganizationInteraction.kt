package com.chocolate.viewmodel.createOrganization

import android.net.Uri

interface CreateOrganizationInteraction {
    fun onOrganizationNameChange(organizationName: String)
    fun onClickHaveOrganization()
    fun onClickNextButton()
    fun onOrganizationImageChange(imageUri: Uri)
}
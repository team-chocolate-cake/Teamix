package com.chocolate.viewmodel.createOrganization

interface CreateOrganizationInteraction {
    fun onOrganizationNameChange(organizationName: String)
    fun onClickHaveOrganization()
    fun onClickNextButton(organizationName: String)
}
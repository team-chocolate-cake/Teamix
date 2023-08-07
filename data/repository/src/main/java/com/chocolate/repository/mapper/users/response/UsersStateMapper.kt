package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.respons.IagoZulipCom
import com.chocolate.entities.user.respons.Presences
import com.chocolate.entities.user.respons.UsersState
import com.chocolate.repository.dto.users.response.IagoZulipComDto
import com.chocolate.repository.dto.users.response.PresencesDto
import com.chocolate.repository.dto.users.response.UsersStateDto

fun UsersStateDto.toUsersState(): UsersState {
return UsersState(presencesDto?.toPresences(),serverTimestamp)
}

fun PresencesDto.toPresences(): Presences {
    return Presences(iagoZulipComDto?.toIagoZulipCom())
}


fun IagoZulipComDto.toIagoZulipCom(): IagoZulipCom {
    return IagoZulipCom(aggregated?.toAggregated(),websiteDto?.toWebsite())
}
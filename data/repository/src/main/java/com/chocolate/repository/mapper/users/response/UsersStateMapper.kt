package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.Aggregated
import com.chocolate.entities.user.IagoZulipCom
import com.chocolate.entities.user.Presence
import com.chocolate.entities.user.Presences
import com.chocolate.entities.user.UsersState
import com.chocolate.entities.user.Website
import com.chocolate.repository.dto.users.response.IagoZulipComDto
import com.chocolate.repository.dto.users.response.PresencesDto
import com.chocolate.repository.dto.users.response.UsersStateDto

fun UsersStateDto.toUsersState(): UsersState {
return UsersState(
    presencesDto?.toPresences()?: Presences(IagoZulipCom(Aggregated("",0),
        Website("",0)  ))
    ,serverTimestamp?:0.0)
}

fun PresencesDto.toPresences(): Presences {
    return Presences(iagoZulipComDto?.toIagoZulipCom()
        ?: IagoZulipCom(Aggregated("",0),
            Website("",0)  )
    )
}


fun IagoZulipComDto.toIagoZulipCom(): IagoZulipCom {
    return IagoZulipCom(
     aggregatedDto?.toAggregated() ?: Aggregated("",0),
    websiteDto?.toWebsite() ?: Website("",0))
}
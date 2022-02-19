package xyz.neonkid.cms.modules.user.domain.aggregate

import xyz.neonkid.cms.modules.user.domain.valueObjects.Email
import xyz.neonkid.cms.modules.user.domain.valueObjects.NickName

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
data class User (
    val id: UserId,
    val email: Email,
    val nickName: NickName
)
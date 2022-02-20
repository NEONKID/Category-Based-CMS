package xyz.neonkid.cms.modules.user.domain.aggregate

import xyz.neonkid.cms.modules.user.domain.valueObjects.Email
import xyz.neonkid.cms.modules.user.domain.valueObjects.NickName
import xyz.neonkid.cms.modules.user.domain.valueObjects.UserDateTime
import xyz.neonkid.cms.modules.user.useCases.commands.CreateUserCommand

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
data class User (
    val id: UserId,
    val email: Email,
    val nickName: NickName,
    val createdAt: UserDateTime?,
    val updatedAt: UserDateTime?
) {
    companion object {
        fun newUser(command: CreateUserCommand) = User(
            UserId(0),
            command.email,
            command.nickname,
            null, null
        )
    }
}
package xyz.neonkid.cms.modules.user.domain.aggregate

import xyz.neonkid.cms.modules.user.domain.valueObjects.*
import xyz.neonkid.cms.modules.user.useCases.commands.CreateUserCommand

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
data class User (
    val id: UserId,
    val email: Email,
    var nickName: NickName,
    var password: Password,
    var isAdmin: IsAdmin,
    val createdAt: UserDateTime?,
    val updatedAt: UserDateTime?
) {
    companion object {
        fun newUser(command: CreateUserCommand) = User(
            UserId(0),
            command.email,
            command.nickname,
            command.password,
            IsAdmin(false),
            null, null
        )
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
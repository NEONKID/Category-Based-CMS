package xyz.neonkid.cms.modules.user.infrastructure.persistence

import xyz.neonkid.cms.common.interfaces.ModelMapper
import xyz.neonkid.cms.modules.user.domain.aggregate.User
import xyz.neonkid.cms.modules.user.domain.aggregate.UserId
import xyz.neonkid.cms.modules.user.domain.valueObjects.*
import xyz.neonkid.cms.persistence.user.UserEntity

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
object UserMapper : ModelMapper<User, UserEntity> {
    override fun mapToDomainEntity(model: UserEntity) =
        User(
            UserId(model.id),
            Email(model.email),
            NickName(model.nickname),
            Password(model.password),
            IsAdmin(model.isAdmin),
            model.createdAt?.let { UserDateTime(model.createdAt) },
            UserDateTime(model.updatedAt)
        )

    override fun mapToJdbcEntity(model: User) =
        UserEntity(
            model.id.value,
            model.email.value,
            model.nickName.value,
            model.password.value,
            model.isAdmin.value,
            model.createdAt?.value
        )
}
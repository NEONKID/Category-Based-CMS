package xyz.neonkid.cms.modules.user.useCases.queries

import org.jooq.DSLContext
import org.jooq.impl.DSL.*
import org.springframework.stereotype.Repository
import xyz.neonkid.cms.modules.user.useCases.exceptions.UserNotFoundException
import xyz.neonkid.cms.modules.user.useCases.queries.dto.UserDTO
import xyz.neonkid.cms.tables.UserPost
import xyz.neonkid.cms.tables.Users

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Repository
class UserJooqRepository(private val ctx: DSLContext) : UserQueryRepository {
    private val user: Users = Users.USERS
    private val userPost: UserPost = UserPost.USER_POST

    private val selectUsers = jsonObject(
        key("id").value(user.ID),
        key("email").value(user.EMAIL),
        key("nickname").value(user.NICKNAME)
    )

    override fun fetchAll(): MutableList<UserDTO> = ctx.select(selectUsers).from(user).fetch().into(UserDTO::class.java)

    override fun fetchById(id: Long) =
        ctx.select(selectUsers).from(user).where(user.ID.eq(id))
            .fetchOneInto(UserDTO::class.java) ?: throw UserNotFoundException(id)
}
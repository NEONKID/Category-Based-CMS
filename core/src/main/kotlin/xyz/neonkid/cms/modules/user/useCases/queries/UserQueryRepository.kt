package xyz.neonkid.cms.modules.user.useCases.queries

import xyz.neonkid.cms.modules.user.useCases.queries.dto.UserDTO

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface UserQueryRepository {
    fun fetchById(id: Long): UserDTO
    fun fetchAll(): MutableList<UserDTO>
}
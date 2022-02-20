package xyz.neonkid.cms.modules.user.useCases.queries

import org.jooq.DSLContext
import org.springframework.stereotype.Repository

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Repository
class UserJooqRepository(private val ctx: DSLContext) : UserQueryRepository {

}
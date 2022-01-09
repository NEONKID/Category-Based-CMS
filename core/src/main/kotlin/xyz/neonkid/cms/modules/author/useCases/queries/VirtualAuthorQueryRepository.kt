package xyz.neonkid.cms.modules.author.useCases.queries

import xyz.neonkid.cms.modules.author.useCases.queries.dto.SingleVirtualAuthorDTO
import xyz.neonkid.cms.modules.author.useCases.queries.dto.VirtualAuthorDTO
import java.util.*

/**
 * Created by Neon K.I.D on 1/4/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface VirtualAuthorQueryRepository {
    fun fetchAll(): MutableList<SingleVirtualAuthorDTO>
    fun fetchById(id: UUID): VirtualAuthorDTO
}
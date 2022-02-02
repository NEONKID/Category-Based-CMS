package xyz.neonkid.cms.modules.tag.useCases.queries

import xyz.neonkid.cms.modules.tag.useCases.queries.dto.PublicSingleTagDTO
import xyz.neonkid.cms.modules.tag.useCases.queries.dto.PublicTagDTO
import xyz.neonkid.cms.modules.tag.useCases.queries.dto.SingleTagDTO
import xyz.neonkid.cms.modules.tag.useCases.queries.dto.TagDTO

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface TagQueryRepository {
    fun fetchById(name: String): TagDTO
    fun fetchPublicByName(name: String): PublicTagDTO
    fun fetchPublicAll(): MutableList<PublicSingleTagDTO>
    fun fetchAll(): MutableList<SingleTagDTO>
}
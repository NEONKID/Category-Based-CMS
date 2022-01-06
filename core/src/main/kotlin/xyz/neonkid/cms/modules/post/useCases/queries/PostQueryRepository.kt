package xyz.neonkid.cms.modules.post.useCases.queries

import xyz.neonkid.cms.modules.post.useCases.queries.dto.PostDTO
import xyz.neonkid.cms.modules.post.useCases.queries.dto.SinglePostDTO

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface PostQueryRepository {
    fun fetchByTitle(title: String): PostDTO
    fun fetchById(id: Long): PostDTO
    fun fetchPublicById(id: Long): PostDTO
    fun fetchAll(): MutableList<SinglePostDTO>
    fun fetchPublicAll(): MutableList<SinglePostDTO>
}
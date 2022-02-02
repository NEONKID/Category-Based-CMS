package xyz.neonkid.cms.modules.tag.useCases.queries.dto

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class TagDTO (
    val name: String,
    val postCount: Int,
    val publicPostCount: Int
)
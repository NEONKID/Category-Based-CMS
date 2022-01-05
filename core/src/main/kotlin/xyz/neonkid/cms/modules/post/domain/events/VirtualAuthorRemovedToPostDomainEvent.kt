package xyz.neonkid.cms.modules.post.domain.events

import java.util.*

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class VirtualAuthorRemovedToPostDomainEvent(
    val postId: Long,
    val virtualAuthorId: UUID
)
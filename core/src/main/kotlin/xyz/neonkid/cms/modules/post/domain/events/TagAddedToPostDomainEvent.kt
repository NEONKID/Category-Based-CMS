package xyz.neonkid.cms.modules.post.domain.events

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class TagAddedToPostDomainEvent (val postId: Long, val tagName: String)
package xyz.neonkid.cms.modules.tag.domain.aggregate

import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.tag.useCases.events.AddPostToTagCommand
import xyz.neonkid.cms.modules.tag.useCases.events.RemovePostToTagCommand

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
data class Tag (val name: TagId, var postIds: Set<PostId> = setOf()) {
    fun addPost(command: AddPostToTagCommand) {
        val postIds = this.postIds.toMutableSet()
        postIds.add(command.postId)

        this.postIds = postIds
    }

    fun removePost(command: RemovePostToTagCommand) {
        val postIds = this.postIds.toMutableSet()
        postIds.remove(command.postId)

        this.postIds = postIds
    }
}
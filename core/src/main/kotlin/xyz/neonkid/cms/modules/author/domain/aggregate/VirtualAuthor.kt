package xyz.neonkid.cms.modules.author.domain.aggregate

import xyz.neonkid.cms.modules.author.domain.valueObjects.Description
import xyz.neonkid.cms.modules.author.domain.valueObjects.Name
import xyz.neonkid.cms.modules.author.domain.valueObjects.ProfileImage
import xyz.neonkid.cms.modules.author.useCases.commands.CreateVirtualAuthorCommand
import xyz.neonkid.cms.modules.author.useCases.events.AddPostToVirtualAuthorCommand
import xyz.neonkid.cms.modules.author.useCases.events.RemovePostToVirtualAuthorCommand
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import java.util.*

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class VirtualAuthor (
    val id: VirtualAuthorId?,
    var name: Name,
    var description: Description?,
    var profileImage: ProfileImage?,
    var postIds: Set<PostId> = setOf()
) {
    companion object {
        fun newVirtualAuthor(command: CreateVirtualAuthorCommand) =
            VirtualAuthor(
                null,
                command.name,
                command.description,
                command.profileImage,
                setOf()
            )
    }

    fun addPost(command: AddPostToVirtualAuthorCommand) {
        val postIds = this.postIds.toMutableSet()
        postIds.add(command.postId)

        this.postIds = postIds
    }

    fun removePost(command: RemovePostToVirtualAuthorCommand) {
        val postIds = this.postIds.toMutableSet()
        postIds.remove(command.postId)

        this.postIds = postIds
    }
}
package xyz.neonkid.cms.modules.post.domain.aggregate

import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.post.domain.valueObjects.*
import xyz.neonkid.cms.modules.post.useCases.commands.CreatePostCommand
import java.time.LocalDateTime

/**
 * Created by Neon K.I.D on 1/1/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class Post (
    val id: PostId,
    var title: Title,
    var body: Body?,
    var thumbnail: Thumbnail?,
    var isPrivate: IsPrivate,
    var description: Description?,
    var publishedAt: ContentDateTime?,
    var categoryId: CategoryId?,
    var virtualAuthorId: VirtualAuthorId?
) {
    companion object {
        fun newPost(command: CreatePostCommand) = Post(
            PostId.nextId(),
            command.title,
            command.body,
            command.thumbnail,
            command.isPrivate,
            command.description,
            command.publishedAt,
            command.categoryId,
            command.virtualAuthorId
        )
    }

    fun setPublishedAtNow() {
        if (this.publishedAt == null)
            this.publishedAt = ContentDateTime(LocalDateTime.now())
    }
}
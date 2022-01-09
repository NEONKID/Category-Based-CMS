package xyz.neonkid.cms.modules.post.domain.aggregate

import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.post.domain.valueObjects.*
import xyz.neonkid.cms.modules.post.useCases.commands.CreatePostCommand
import xyz.neonkid.cms.modules.post.useCases.commands.SavePostCommand
import xyz.neonkid.cms.modules.tag.domain.aggregate.TagId
import java.time.LocalDateTime
import java.util.stream.Collectors

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
    var virtualAuthorId: VirtualAuthorId?,
    var tags: Set<TagId> = setOf()
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
            command.virtualAuthorId,
            command.tags
        )
    }

    fun updatePost(command: SavePostCommand) {
        command.title?.let { this.title = it }
        command.body?.let { this.body = it }
        command.thumbnail?.let { this.thumbnail = it }
        command.description?.let { this.description = it }
        command.publishedAt?.let { this.publishedAt = it }
    }

    fun setPublishedAtNow() {
        if (this.publishedAt == null)
            this.publishedAt = ContentDateTime(LocalDateTime.now())
    }
}
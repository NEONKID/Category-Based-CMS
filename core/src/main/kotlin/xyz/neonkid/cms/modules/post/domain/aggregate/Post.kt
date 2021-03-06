package xyz.neonkid.cms.modules.post.domain.aggregate

import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.post.domain.valueObjects.*
import xyz.neonkid.cms.modules.post.useCases.commands.CreatePostCommand
import xyz.neonkid.cms.modules.post.useCases.commands.SavePostCommand
import xyz.neonkid.cms.modules.tag.domain.aggregate.TagId
import xyz.neonkid.cms.modules.user.domain.aggregate.UserId
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
    val userId: UserId,
    var virtualAuthorId: VirtualAuthorId?,
    var tags: Set<TagId> = setOf(),
    var createdAt: ContentDateTime?,
    var updatedAt: ContentDateTime?
) {
    companion object {
        fun newPost(command: CreatePostCommand) = Post(
            PostId(0),
            command.title,
            command.body,
            command.thumbnail,
            command.isPrivate,
            command.description,
            command.publishedAt,
            command.categoryId,
            command.userId,
            command.virtualAuthorId,
            command.tags,
            null, null
        )
    }

    fun addTags(tags: Set<TagId>) {
        val newTags = tags.toMutableSet()
        newTags.addAll(tags)

        this.tags = newTags
    }

    fun removeTags(tags: Set<TagId>) {
        val newTags = tags.toMutableSet()
        newTags.removeAll(tags)

        this.tags = newTags
    }

    fun updatePost(command: SavePostCommand) {
        command.title?.let { this.title = it }
        command.body?.let { this.body = it }
        command.isPrivate?.let { this.isPrivate = it }
        command.thumbnail?.let { this.thumbnail = it }
        command.description?.let { this.description = it }
        command.publishedAt?.let { this.publishedAt = it }
    }

    fun setPublishedAtNow() {
        if (this.publishedAt == null)
            this.publishedAt = ContentDateTime(LocalDateTime.now())
    }
}
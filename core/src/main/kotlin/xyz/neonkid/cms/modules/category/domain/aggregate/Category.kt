package xyz.neonkid.cms.modules.category.domain.aggregate

import xyz.neonkid.cms.modules.category.domain.valueObjects.Name
import xyz.neonkid.cms.modules.category.useCases.commands.CreateCategoryCommand
import xyz.neonkid.cms.modules.category.useCases.events.AddPostToCategoryCommand
import xyz.neonkid.cms.modules.category.useCases.events.RemovePostToCategoryCommand
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId

/**
 * Created by Neon K.I.D on 1/4/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
data class Category(
    val id: CategoryId,
    var name: Name,
    var postIds: Set<PostId> = setOf()
) {
    companion object {
        fun newCategory(command: CreateCategoryCommand) = Category(CategoryId(0), command.name, setOf())
    }

    fun addPost(command: AddPostToCategoryCommand) {
        val postIds = this.postIds.toMutableSet()
        postIds.add(command.postId)

        this.postIds = postIds
    }

    fun removePost(command: RemovePostToCategoryCommand) {
        val postIds = this.postIds.toMutableSet()
        postIds.remove(command.postId)

        this.postIds = postIds
    }
}
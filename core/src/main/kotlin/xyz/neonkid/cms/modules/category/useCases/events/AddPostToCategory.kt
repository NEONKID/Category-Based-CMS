package xyz.neonkid.cms.modules.category.useCases.events

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.category.infrastructure.persistence.CategoryPersistenceAdapter
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.CategoryAddedToPostDomainEvent

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Component
class AddPostToCategoryEventHandler(private val addPostToCategoryUseCase: AddPostToCategoryUseCase) {
    @EventListener
    fun handleEvent(event: CategoryAddedToPostDomainEvent) {
        val command = AddPostToCategoryCommand(PostId(event.postId), CategoryId(event.categoryId))
        this.addPostToCategoryUseCase.invoke(command)
    }
}

@Service
@Transactional
class AddPostToCategoryUseCase(
    private val categoryPersistenceAdapter: CategoryPersistenceAdapter
) : UseCase<AddPostToCategoryCommand, Unit> {
    override fun invoke(command: AddPostToCategoryCommand) {
        val category = categoryPersistenceAdapter.findById(command.categoryId)
        category.addPost(command)

        categoryPersistenceAdapter.update(category)
    }
}

data class AddPostToCategoryCommand(val postId: PostId, val categoryId: CategoryId)
package xyz.neonkid.cms.modules.category.useCases.events

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.category.infrastructure.persistence.CategoryPersistenceAdapter
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.CategoryRemovedToPostDomainEvent

/**
 * Created by Neon K.I.D on 1/4/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Component
class RemovePostToCategoryEventHandler(private val removePostToCategoryUseCase: RemovePostToCategoryUseCase) {
    @EventListener
    fun handleEvent(event: CategoryRemovedToPostDomainEvent) {
        val command = RemovePostToCategoryCommand(PostId(event.postId), CategoryId(event.categoryId))
        this.removePostToCategoryUseCase.invoke(command)
    }
}

@Service
@Transactional
class RemovePostToCategoryUseCase(
    private val categoryPersistenceAdapter: CategoryPersistenceAdapter
) : UseCase<RemovePostToCategoryCommand, Unit> {
    override fun invoke(command: RemovePostToCategoryCommand) {
        val category = categoryPersistenceAdapter.findById(command.categoryId)
        category.removePost(command)

        categoryPersistenceAdapter.update(category)
    }
}

data class RemovePostToCategoryCommand(val postId: PostId, val categoryId: CategoryId)
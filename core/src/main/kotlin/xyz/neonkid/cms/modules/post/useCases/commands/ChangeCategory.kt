package xyz.neonkid.cms.modules.post.useCases.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.CategoryAddedToPostDomainEvent
import xyz.neonkid.cms.modules.post.domain.events.CategoryRemovedToPostDomainEvent
import xyz.neonkid.cms.modules.post.infrastructure.persistence.PostPersistenceAdapter

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class ChangeCategoryUseCase(
    private val postPersistenceAdapter: PostPersistenceAdapter,
    private val publisher: ApplicationEventPublisher
): UseCase<ChangeCategoryCommand, Unit> {
    override fun invoke(command: ChangeCategoryCommand) {
        val entity = postPersistenceAdapter.findById(command.postId)
        if (entity.categoryId != null)
            this.publisher.publishEvent(
                CategoryRemovedToPostDomainEvent(entity.id.value, entity.categoryId!!.value)
            )

        entity.categoryId = command.categoryId
        this.publisher.publishEvent(
            CategoryAddedToPostDomainEvent(entity.id.value, command.categoryId.value)
        )

        postPersistenceAdapter.update(entity)
    }
}

data class ChangeCategoryCommand(val postId: PostId, val categoryId: CategoryId)
package xyz.neonkid.cms.modules.post.useCases.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.CategoryAddedToPostDomainEvent
import xyz.neonkid.cms.modules.post.infrastructure.persistence.PostPersistenceAdapter

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class AddCategoryUseCase(
    private val postPersistenceAdapter: PostPersistenceAdapter,
    private val publisher: ApplicationEventPublisher
): UseCase<AddCategoryCommand, Unit> {
    override fun invoke(command: AddCategoryCommand) {
        val entity = postPersistenceAdapter.findById(command.postId)
        entity.categoryId = command.categoryId

        this.publisher.publishEvent(CategoryAddedToPostDomainEvent(entity.id.value, command.categoryId.value))
        postPersistenceAdapter.update(entity)
    }
}

data class AddCategoryCommand(val postId: PostId, val categoryId: CategoryId)
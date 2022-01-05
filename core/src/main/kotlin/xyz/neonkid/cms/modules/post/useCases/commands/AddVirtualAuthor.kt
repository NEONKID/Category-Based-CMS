package xyz.neonkid.cms.modules.post.useCases.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.VirtualAuthorAddedToPostDomainEvent
import xyz.neonkid.cms.modules.post.infrastructure.persistence.PostPersistenceAdapter

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class AddVirtualAuthorUseCase(
    private val postPersistenceAdapter: PostPersistenceAdapter,
    private val publisher: ApplicationEventPublisher
): UseCase<AddVirtualAuthorCommand, Unit> {
    override fun invoke(command: AddVirtualAuthorCommand) {
        val entity = postPersistenceAdapter.findById(command.postId)
        entity.virtualAuthorId = command.virtualAuthorId

        this.publisher.publishEvent(VirtualAuthorAddedToPostDomainEvent(entity.id.value, command.virtualAuthorId.value))
        postPersistenceAdapter.update(entity)
    }
}

data class AddVirtualAuthorCommand(val postId: PostId, val virtualAuthorId: VirtualAuthorId)
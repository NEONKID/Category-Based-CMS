package xyz.neonkid.cms.modules.post.useCases.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.TagAddedToPostDomainEvent
import xyz.neonkid.cms.modules.post.infrastructure.persistence.PostPersistenceAdapter
import xyz.neonkid.cms.modules.tag.domain.aggregate.TagId

/**
 * Created by Neon K.I.D on 1/8/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class AddTagUseCase(
    private val postPersistenceAdapter: PostPersistenceAdapter,
    private val publisher: ApplicationEventPublisher
): UseCase<AddTagCommand, Unit> {
    override fun invoke(command: AddTagCommand) {
        val post = postPersistenceAdapter.findById(command.postId)
        post.addTags(command.tagIds)

        for (tag in post.tags)
            this.publisher.publishEvent(TagAddedToPostDomainEvent(post.id.value, tag.value))

        postPersistenceAdapter.update(post)
    }
}

data class AddTagCommand(val postId: PostId, val tagIds: Set<TagId>)
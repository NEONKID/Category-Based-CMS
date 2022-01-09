package xyz.neonkid.cms.modules.tag.useCases.events

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.TagRemovedToPostDomainEvent
import xyz.neonkid.cms.modules.tag.infrastructure.persistence.TagPersistenceAdapter

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Component
class RemovePostToTagEventHandler(private val removePostToTagUseCase: RemovePostToTagUseCase) {
    @EventListener
    fun handleEvent(event: TagRemovedToPostDomainEvent) {
        val command = RemovePostToTagCommand(PostId(event.postId), event.tagName)
        this.removePostToTagUseCase.invoke(command)
    }
}

@Service
@Transactional
class RemovePostToTagUseCase(
    private val tagPersistenceAdapter: TagPersistenceAdapter
): UseCase<RemovePostToTagCommand, Unit> {
    override fun invoke(command: RemovePostToTagCommand) {
        val tag = tagPersistenceAdapter.findById(command.tagName)
        tag.removePost(command)

        tagPersistenceAdapter.update(tag)
    }
}

data class RemovePostToTagCommand(val postId: PostId, val tagName: String)
package xyz.neonkid.cms.modules.tag.useCases.events

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.TagAddedToPostDomainEvent
import xyz.neonkid.cms.modules.tag.domain.aggregate.Tag
import xyz.neonkid.cms.modules.tag.domain.aggregate.TagId
import xyz.neonkid.cms.modules.tag.infrastructure.persistence.TagMapper
import xyz.neonkid.cms.modules.tag.infrastructure.persistence.TagPersistenceAdapter
import xyz.neonkid.cms.persistence.tag.TagRepository

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Component
class AddPostToTagEventHandler(private val addPostToTagUseCase: AddPostToTagUseCase) {
    @EventListener
    fun handleEvent(event: TagAddedToPostDomainEvent) {
        val command = AddPostToTagCommand(PostId(event.postId), TagId(event.tagName))
        this.addPostToTagUseCase.invoke(command)
    }
}

@Service
@Transactional
class AddPostToTagUseCase(
    private val tagPersistenceAdapter: TagPersistenceAdapter,
    private val tagRepository: TagRepository
): UseCase<AddPostToTagCommand, Unit> {
    override fun invoke(command: AddPostToTagCommand) {
        val entity = tagRepository.findById(command.tagName.value.trim()).orElse(null)
        val tag = if (entity == null) Tag(TagId(command.tagName.value.trim())) else TagMapper.mapToDomainEntity(entity)
        tag.addPost(command)

        if (entity == null) tagPersistenceAdapter.insert(tag)
        else tagPersistenceAdapter.update(tag)
    }
}

data class AddPostToTagCommand(val postId: PostId, val tagName: TagId)
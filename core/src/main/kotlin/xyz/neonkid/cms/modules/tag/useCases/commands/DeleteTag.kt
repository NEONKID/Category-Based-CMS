package xyz.neonkid.cms.modules.tag.useCases.commands

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.tag.infrastructure.persistence.TagPersistenceAdapter
import xyz.neonkid.cms.modules.tag.useCases.exceptions.TagDependOnPostException
import xyz.neonkid.cms.persistence.tag.TagRepository
import java.lang.RuntimeException

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class DeleteTagUseCase (
    private val tagPersistenceAdapter: TagPersistenceAdapter
): UseCase<DeleteTagCommand, Unit> {
    override fun invoke(command: DeleteTagCommand) {
        val entity = tagPersistenceAdapter.findById(command.tagName)
        if (entity.postIds.isNotEmpty())
            throw TagDependOnPostException(entity.name.value)

        tagPersistenceAdapter.deleteById(command.tagName)
    }
}

data class DeleteTagCommand(val tagName: String)
package xyz.neonkid.cms.modules.category.useCases.commands

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.category.infrastructure.persistence.CategoryPersistenceAdapter
import xyz.neonkid.cms.modules.category.useCases.exceptions.CategoryDependOnPostException

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class DeleteCategoryUseCase(
    private val categoryPersistenceAdapter: CategoryPersistenceAdapter
): UseCase<DeleteCategoryCommand, Unit> {
    override fun invoke(command: DeleteCategoryCommand) {
        val entity = categoryPersistenceAdapter.findById(command.categoryId)
        if (entity.postIds.isNotEmpty())
            throw CategoryDependOnPostException(entity.id.value)

        categoryPersistenceAdapter.deleteById(command.categoryId)
    }
}

data class DeleteCategoryCommand(val categoryId: CategoryId)
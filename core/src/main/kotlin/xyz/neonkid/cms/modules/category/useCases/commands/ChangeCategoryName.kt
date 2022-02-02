package xyz.neonkid.cms.modules.category.useCases.commands

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.category.domain.valueObjects.Name
import xyz.neonkid.cms.modules.category.infrastructure.persistence.CategoryPersistenceAdapter

/**
 * Created by Neon K.I.D on 1/3/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class ChangeCategoryNameUseCase(
    private val categoryPersistenceAdapter: CategoryPersistenceAdapter
) : UseCase<ChangeCategoryNameCommand, Unit> {
    override fun invoke(command: ChangeCategoryNameCommand) {
        val entity = categoryPersistenceAdapter.findById(command.categoryId)
        entity.name = command.name

        categoryPersistenceAdapter.update(entity)
    }
}

data class ChangeCategoryNameCommand(val categoryId: CategoryId, val name: Name)
data class ChangeCategoryNameRequest(val name: String)
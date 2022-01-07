package xyz.neonkid.cms.modules.category.useCases.commands

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.category.domain.aggregate.Category
import xyz.neonkid.cms.modules.category.domain.valueObjects.Name
import xyz.neonkid.cms.modules.category.infrastructure.persistence.CategoryPersistenceAdapter
import xyz.neonkid.cms.modules.category.useCases.queries.CategoryQueryRepository
import xyz.neonkid.cms.modules.category.useCases.queries.dto.CategoryDTO

/**
 * Created by Neon K.I.D on 1/3/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class CreateCategoryUseCase(
    private val categoryPersistenceAdapter: CategoryPersistenceAdapter,
    private val categoryQueryRepository: CategoryQueryRepository
) : UseCase<CreateCategoryCommand, CategoryDTO> {
    override fun invoke(command: CreateCategoryCommand): CategoryDTO =
        categoryQueryRepository.fetchById(categoryPersistenceAdapter.insert(Category.newCategory(command)).id.value)
}

data class CreateCategoryCommand(val name: Name)
data class CreateCategoryRequest(val name: String)
package xyz.neonkid.cms.modules.category.infrastructure.persistence

import org.springframework.stereotype.Service
import xyz.neonkid.cms.common.interfaces.PersistenceAdapter
import xyz.neonkid.cms.modules.category.domain.aggregate.Category
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.category.useCases.exceptions.CategoryNotFoundException
import xyz.neonkid.cms.persistence.category.CategoryRepository

/**
 * Created by Neon K.I.D on 1/4/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
class CategoryPersistenceAdapter(
    private val categoryRepository: CategoryRepository
) : PersistenceAdapter<Category, CategoryId> {
    override fun findById(id: CategoryId) =
        CategoryMapper.mapToDomainEntity(categoryRepository.findById(id.value).orElseThrow { CategoryNotFoundException(id.value) })

    override fun insert(domain: Category) =
        CategoryMapper.mapToDomainEntity(categoryRepository.insert(CategoryMapper.mapToJdbcEntity(domain)))

    override fun update(domain: Category) =
        CategoryMapper.mapToDomainEntity(categoryRepository.update(CategoryMapper.mapToJdbcEntity(domain)))

    override fun deleteById(id: CategoryId) {
        categoryRepository.deleteById(id.value)
    }
}
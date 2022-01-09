package xyz.neonkid.cms.modules.category.infrastructure.persistence

import xyz.neonkid.cms.common.interfaces.ModelMapper
import xyz.neonkid.cms.modules.category.domain.aggregate.Category
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.category.domain.valueObjects.Name
import xyz.neonkid.cms.persistence.category.CategoryEntity
import xyz.neonkid.cms.persistence.category.PostRef

/**
 * Created by Neon K.I.D on 1/4/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
object CategoryMapper : ModelMapper<Category, CategoryEntity> {
    override fun mapToDomainEntity(model: CategoryEntity) = Category(CategoryId(model.id), Name(model.name))
    override fun mapToJdbcEntity(model: Category) = CategoryEntity(
        model.id.value, model.name.value, model.postIds.map { PostRef(it.value) }.toSet()
    )
}
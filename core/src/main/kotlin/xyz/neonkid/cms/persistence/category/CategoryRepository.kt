package xyz.neonkid.cms.persistence.category

import org.springframework.data.repository.CrudRepository
import xyz.neonkid.cms.core.jdbc.WithInsertAndUpdate

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface CategoryRepository : CrudRepository<CategoryEntity, Long>, WithInsertAndUpdate<CategoryEntity>